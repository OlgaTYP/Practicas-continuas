package com.example.todoapi.controller;

import com.example.todoapi.dto.TaskRequest;
import com.example.todoapi.model.Task;
import com.example.todoapi.model.TaskPriority;
import com.example.todoapi.model.TaskStatus;
import com.example.todoapi.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public List<Task> getAllTasks(
      @RequestParam(required = false) TaskStatus status,
      @RequestParam(required = false) TaskPriority priority) {
    return taskService.getAllTasks(status, priority);
  }

  @GetMapping("/{id}")
  public Task getTaskById(@PathVariable("id") Long id) {
    return taskService.getTaskById(id);
  }

  @PostMapping
  public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest request) {
    Task createdTask = taskService.createTask(request.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
  }

  @PutMapping("/{id}")
  public Task updateTask(@PathVariable("id") Long id, @Valid @RequestBody TaskRequest request) {
    return taskService.updateTask(id, request.toEntity());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
    taskService.deleteTask(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/done")
  public Task markTaskAsDone(@PathVariable("id") Long id) {
    return taskService.markTaskAsDone(id);
  }
}
