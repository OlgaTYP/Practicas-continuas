package com.example.todoapi.service;

import com.example.todoapi.exception.BusinessRuleException;
import com.example.todoapi.exception.ResourceNotFoundException;
import com.example.todoapi.model.Task;
import com.example.todoapi.model.TaskPriority;
import com.example.todoapi.model.TaskStatus;
import com.example.todoapi.repository.TaskRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> getAllTasks(TaskStatus status, TaskPriority priority, String title) {
    String titleFilter =
        title == null || title.isBlank() ? null : title.trim().toLowerCase(Locale.ROOT);

    return taskRepository.findAll().stream()
        .filter(task -> status == null || task.getStatus() == status)
        .filter(task -> priority == null || task.getPriority() == priority)
        .filter(
            task ->
                titleFilter == null
                    || task.getTitle().toLowerCase(Locale.ROOT).contains(titleFilter))
        .toList();
  }

  public Task getTaskById(Long id) {
    return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", id));
  }

  public Task createTask(Task task) {
    validateTask(task);

    LocalDateTime now = LocalDateTime.now();
    task.setId(null);
    task.setStatus(task.getStatus() == null ? TaskStatus.TODO : task.getStatus());
    task.setPriority(task.getPriority() == null ? TaskPriority.HIGH : task.getPriority());
    task.setCreatedAt(now);
    task.setUpdatedAt(now);

    return taskRepository.save(task);
  }

  public Task updateTask(Long id, Task updatedTask) {
    Task existingTask = getTaskById(id);
    validateTask(updatedTask);

    existingTask.setTitle(updatedTask.getTitle().trim());
    existingTask.setDescription(updatedTask.getDescription().trim());
    existingTask.setPriority(updatedTask.getPriority());
    existingTask.setStatus(updatedTask.getStatus());
    existingTask.setDeadline(updatedTask.getDeadline());
    existingTask.setUpdatedAt(LocalDateTime.now());

    return taskRepository.save(existingTask);
  }

  public void deleteTask(Long id) {
    if (!taskRepository.existsById(id)) {
      throw new ResourceNotFoundException("Task", id);
    }
    taskRepository.deleteById(id);
  }

  public Task markTaskAsDone(Long id) {
    Task task = getTaskById(id);
    if (task.getStatus() == TaskStatus.DONE) {
      throw new BusinessRuleException("Task is already marked as done");
    }

    task.setStatus(TaskStatus.DONE);
    task.setUpdatedAt(LocalDateTime.now());
    return taskRepository.save(task);
  }

  public void validateTask(Task task) {
    if (task == null) {
      throw new BusinessRuleException("Task payload is required");
    }

    if (task.getTitle() == null || task.getTitle().isBlank()) {
      throw new BusinessRuleException("Title is required");
    }
    if (task.getTitle().trim().length() < 3 || task.getTitle().trim().length() > 150) {
      throw new BusinessRuleException("Title must be between 3 and 150 characters");
    }

    if (task.getDescription() == null || task.getDescription().isBlank()) {
      throw new BusinessRuleException("Description is required");
    }
    if (task.getDescription().trim().length() < 5 || task.getDescription().trim().length() > 1000) {
      throw new BusinessRuleException("Description must be between 5 and 1000 characters");
    }

    if (task.getDeadline() == null) {
      throw new BusinessRuleException("Deadline is required");
    }
    if (task.getDeadline().isBefore(LocalDate.now())) {
      throw new BusinessRuleException("Deadline cannot be in the past");
    }

    if (task.getStatus() == null) {
      throw new BusinessRuleException("Status is required");
    }
    if (task.getPriority() == null) {
      throw new BusinessRuleException("Priority is required");
    }
  }
}
