package com.example.todoapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.todoapi.dto.TaskRequest;
import com.example.todoapi.model.SortDirection;
import com.example.todoapi.model.Task;
import com.example.todoapi.model.TaskPriority;
import com.example.todoapi.model.TaskSortField;
import com.example.todoapi.model.TaskStatus;
import com.example.todoapi.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private TaskService taskService;

  @Test
  void updateTask_shouldReturnUpdatedTask() throws Exception {
    TaskRequest request =
        new TaskRequest(
            "Tarea actualizada",
            "Descripción actualizada",
            TaskStatus.IN_PROGRESS,
            TaskPriority.MEDIUM,
            LocalDate.now().plusDays(5));

    Task updatedTask = new Task();
    updatedTask.setId(1L);
    updatedTask.setTitle(request.title());
    updatedTask.setDescription(request.description());
    updatedTask.setStatus(request.status());
    updatedTask.setPriority(request.priority());
    updatedTask.setDeadline(request.deadline());

    when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(updatedTask);

    mockMvc
        .perform(
            put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.title").value("Tarea actualizada"));
  }

  @Test
  void getAllTasks_withFilters_passesFiltersToService() throws Exception {
    when(taskService.getAllTasks(
            TaskStatus.DONE, TaskPriority.URGENT, "review", null, SortDirection.ASC))
        .thenReturn(List.of());

    mockMvc
        .perform(
            get("/api/tasks")
                .param("status", "DONE")
                .param("priority", "URGENT")
                .param("title", "review"))
        .andExpect(status().isOk());

    verify(taskService)
        .getAllTasks(TaskStatus.DONE, TaskPriority.URGENT, "review", null, SortDirection.ASC);
  }

  @Test
  void getAllTasks_withPriorityParam_passesFilterToService() throws Exception {
    when(taskService.getAllTasks(null, TaskPriority.HIGH, null, null, SortDirection.ASC))
        .thenReturn(List.of());

    mockMvc.perform(get("/api/tasks").param("priority", "HIGH")).andExpect(status().isOk());

    verify(taskService).getAllTasks(null, TaskPriority.HIGH, null, null, SortDirection.ASC);
  }

  @Test
  void getAllTasks_withStatusAndPriorityParams_passesBothFiltersToService() throws Exception {
    when(taskService.getAllTasks(TaskStatus.TODO, TaskPriority.HIGH, null, null, SortDirection.ASC))
        .thenReturn(List.of());

    mockMvc
        .perform(get("/api/tasks").param("status", "TODO").param("priority", "HIGH"))
        .andExpect(status().isOk());

    verify(taskService)
        .getAllTasks(TaskStatus.TODO, TaskPriority.HIGH, null, null, SortDirection.ASC);
  }

  @Test
  void getAllTasks_withInvalidStatus_returnsBadRequest() throws Exception {
    mockMvc
        .perform(get("/api/tasks").param("status", "NOT_A_STATUS"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void getAllTasks_withInvalidPriority_returnsBadRequest() throws Exception {
    mockMvc
        .perform(get("/api/tasks").param("priority", "NOT_A_PRIORITY"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void getAllTasks_withSortByDeadline_defaultsToAscending() throws Exception {
    mockMvc.perform(get("/api/tasks").param("sortBy", "deadline")).andExpect(status().isOk());

    verify(taskService).getAllTasks(null, null, null, TaskSortField.DEADLINE, SortDirection.ASC);
  }

  @Test
  void getAllTasks_withSortByDeadlineDesc_passesDirectionToService() throws Exception {
    mockMvc
        .perform(get("/api/tasks").param("sortBy", "deadline").param("direction", "desc"))
        .andExpect(status().isOk());

    verify(taskService).getAllTasks(null, null, null, TaskSortField.DEADLINE, SortDirection.DESC);
  }

  @Test
  void getAllTasks_withFiltersAndSort_passesAllParamsToService() throws Exception {
    mockMvc
        .perform(
            get("/api/tasks")
                .param("status", "TODO")
                .param("sortBy", "deadline")
                .param("direction", "asc"))
        .andExpect(status().isOk());

    verify(taskService)
        .getAllTasks(TaskStatus.TODO, null, null, TaskSortField.DEADLINE, SortDirection.ASC);
  }

  @Test
  void getAllTasks_withInvalidSortBy_returnsBadRequest() throws Exception {
    mockMvc
        .perform(get("/api/tasks").param("sortBy", "title"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Invalid value 'title' for parameter 'sortBy'"));
  }

  @Test
  void getAllTasks_withInvalidDirection_returnsBadRequest() throws Exception {
    mockMvc
        .perform(get("/api/tasks").param("sortBy", "deadline").param("direction", "up"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Invalid value 'up' for parameter 'direction'"));
  }
}
