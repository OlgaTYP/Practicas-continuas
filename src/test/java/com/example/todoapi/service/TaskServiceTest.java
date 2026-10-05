package com.example.todoapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.todoapi.exception.BusinessRuleException;
import com.example.todoapi.exception.ResourceNotFoundException;
import com.example.todoapi.model.Task;
import com.example.todoapi.model.TaskPriority;
import com.example.todoapi.model.TaskStatus;
import com.example.todoapi.repository.TaskRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

  @Mock private TaskRepository taskRepository;

  @InjectMocks private TaskService taskService;

  @Test
  void createTask_shouldRejectDeadlineInThePast() {
    Task task = buildTask(LocalDate.now().minusDays(1));

    BusinessRuleException exception =
        assertThrows(BusinessRuleException.class, () -> taskService.createTask(task));

    assertEquals("Deadline cannot be in the past", exception.getMessage());
    verify(taskRepository, never()).save(any(Task.class));
  }

  @Test
  void createTask_shouldRejectBlankTitle() {
    Task task = buildTask(LocalDate.now().plusDays(4));
    task.setTitle("   ");

    BusinessRuleException exception =
        assertThrows(BusinessRuleException.class, () -> taskService.createTask(task));

    assertEquals("Title is required", exception.getMessage());
    verify(taskRepository, never()).save(any(Task.class));
  }

  @Test
  void createTask_shouldPersistValidTask() {
    Task task = buildTask(LocalDate.now().plusDays(7));
    when(taskRepository.save(any(Task.class)))
        .thenAnswer(
            invocation -> {
              Task savedTask = invocation.getArgument(0);
              savedTask.setId(10L);
              return savedTask;
            });

    Task savedTask = taskService.createTask(task);

    assertNotNull(savedTask.getId());
    assertEquals(TaskStatus.TODO, savedTask.getStatus());
    assertEquals(TaskPriority.HIGH, savedTask.getPriority());
    assertNotNull(savedTask.getCreatedAt());
    assertNotNull(savedTask.getUpdatedAt());
    verify(taskRepository).save(task);
  }

  @Test
  void updateTask_shouldThrowWhenTaskDoesNotExist() {
    Task task = buildTask(LocalDate.now().plusDays(3));
    when(taskRepository.findById(99L)).thenReturn(Optional.empty());

    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> taskService.updateTask(99L, task));

    assertTrue(exception.getMessage().contains("Task with id 99"));
  }

  @Test
  void markTaskAsDone_shouldRejectAlreadyCompletedTask() {
    Task task = buildTask(LocalDate.now().plusDays(6));
    task.setId(7L);
    task.setStatus(TaskStatus.DONE);
    when(taskRepository.findById(7L)).thenReturn(Optional.of(task));

    BusinessRuleException exception =
        assertThrows(BusinessRuleException.class, () -> taskService.markTaskAsDone(7L));

    assertEquals("Task is already marked as done", exception.getMessage());
  }

  @Test
  void deleteTask_shouldRejectMissingTask() {
    when(taskRepository.existsById(42L)).thenReturn(false);

    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> taskService.deleteTask(42L));

    assertTrue(exception.getMessage().contains("Task with id 42"));
  }

  @Test
  void getAllTasks_withoutFilter_returnsAllTasks() {
    Task todoTask = buildTask(LocalDate.now().plusDays(2));
    todoTask.setStatus(TaskStatus.TODO);
    Task doneTask = buildTask(LocalDate.now().plusDays(3));
    doneTask.setStatus(TaskStatus.DONE);
    when(taskRepository.findAll()).thenReturn(List.of(todoTask, doneTask));

    List<Task> tasks = taskService.getAllTasks(null, null, null);

    assertEquals(2, tasks.size());
  }

  @Test
  void getAllTasks_withStatusFilter_returnsOnlyMatchingTasks() {
    Task todoTask = buildTask(LocalDate.now().plusDays(2));
    todoTask.setStatus(TaskStatus.TODO);
    Task doneTask = buildTask(LocalDate.now().plusDays(3));
    doneTask.setStatus(TaskStatus.DONE);
    when(taskRepository.findAll()).thenReturn(List.of(todoTask, doneTask));

    List<Task> tasks = taskService.getAllTasks(TaskStatus.TODO, null, null);

    assertEquals(1, tasks.size());
    assertEquals(TaskStatus.TODO, tasks.get(0).getStatus());
  }

  @Test
  void getAllTasks_withTitleFilter_ignoresCaseAndMatchesPartially() {
    Task matchingTask = buildTask(LocalDate.now().plusDays(2));
    matchingTask.setTitle("Prepare Sprint Review");
    Task nonMatchingTask = buildTask(LocalDate.now().plusDays(3));
    nonMatchingTask.setTitle("Write documentation");
    when(taskRepository.findAll()).thenReturn(List.of(matchingTask, nonMatchingTask));

    List<Task> tasks = taskService.getAllTasks(null, null, "sPrInT");

    assertEquals(1, tasks.size());
    assertEquals("Prepare Sprint Review", tasks.get(0).getTitle());
  }

  @Test
  void getAllTasks_withCombinedFilters_returnsOnlyTasksMatchingEveryFilter() {
    Task matchingTask = buildTask(LocalDate.now().plusDays(2));
    matchingTask.setTitle("Prepare Sprint Review");
    matchingTask.setStatus(TaskStatus.TODO);
    matchingTask.setPriority(TaskPriority.HIGH);

    Task wrongStatusTask = buildTask(LocalDate.now().plusDays(3));
    wrongStatusTask.setTitle("Prepare Sprint Review");
    wrongStatusTask.setStatus(TaskStatus.DONE);
    wrongStatusTask.setPriority(TaskPriority.HIGH);

    Task wrongPriorityTask = buildTask(LocalDate.now().plusDays(4));
    wrongPriorityTask.setTitle("Prepare Sprint Review");
    wrongPriorityTask.setStatus(TaskStatus.TODO);
    wrongPriorityTask.setPriority(TaskPriority.LOW);

    Task wrongTitleTask = buildTask(LocalDate.now().plusDays(5));
    wrongTitleTask.setTitle("Write documentation");
    wrongTitleTask.setStatus(TaskStatus.TODO);
    wrongTitleTask.setPriority(TaskPriority.HIGH);

    when(taskRepository.findAll())
        .thenReturn(List.of(matchingTask, wrongStatusTask, wrongPriorityTask, wrongTitleTask));

    List<Task> tasks = taskService.getAllTasks(TaskStatus.TODO, TaskPriority.HIGH, "sprint");

    assertEquals(1, tasks.size());
    assertEquals("Prepare Sprint Review", tasks.get(0).getTitle());
  }

  @Test
  void getAllTasks_withTitleFilterWithoutMatches_returnsEmptyList() {
    Task task = buildTask(LocalDate.now().plusDays(2));
    when(taskRepository.findAll()).thenReturn(List.of(task));

    List<Task> tasks = taskService.getAllTasks(null, null, "missing");

    assertTrue(tasks.isEmpty());
  }

  @Test
  void getAllTasks_withPriorityFilter_returnsOnlyMatchingTasks() {
    Task highPriorityTask = buildTask(LocalDate.now().plusDays(2));
    highPriorityTask.setPriority(TaskPriority.HIGH);
    Task lowPriorityTask = buildTask(LocalDate.now().plusDays(3));
    lowPriorityTask.setPriority(TaskPriority.LOW);
    when(taskRepository.findAll()).thenReturn(List.of(highPriorityTask, lowPriorityTask));

    List<Task> tasks = taskService.getAllTasks(null, TaskPriority.HIGH, null);

    assertEquals(1, tasks.size());
    assertEquals(TaskPriority.HIGH, tasks.get(0).getPriority());
  }

  @Test
  void getAllTasks_withStatusAndPriorityFilters_returnsOnlyTasksMatchingBoth() {
    Task matchingTask = buildTask(LocalDate.now().plusDays(2));
    matchingTask.setStatus(TaskStatus.TODO);
    matchingTask.setPriority(TaskPriority.HIGH);
    Task wrongStatusTask = buildTask(LocalDate.now().plusDays(3));
    wrongStatusTask.setStatus(TaskStatus.DONE);
    wrongStatusTask.setPriority(TaskPriority.HIGH);
    Task wrongPriorityTask = buildTask(LocalDate.now().plusDays(4));
    wrongPriorityTask.setStatus(TaskStatus.TODO);
    wrongPriorityTask.setPriority(TaskPriority.LOW);
    when(taskRepository.findAll())
        .thenReturn(List.of(matchingTask, wrongStatusTask, wrongPriorityTask));

    List<Task> tasks = taskService.getAllTasks(TaskStatus.TODO, TaskPriority.HIGH, null);

    assertEquals(1, tasks.size());
    assertEquals(TaskStatus.TODO, tasks.get(0).getStatus());
    assertEquals(TaskPriority.HIGH, tasks.get(0).getPriority());
  }

  private Task buildTask(LocalDate deadline) {
    Task task = new Task();
    task.setTitle("Prepare sprint review");
    task.setDescription("Review the sprint with the team and update the backlog");
    task.setStatus(TaskStatus.TODO);
    task.setPriority(TaskPriority.HIGH);
    task.setDeadline(deadline);
    return task;
  }
}
