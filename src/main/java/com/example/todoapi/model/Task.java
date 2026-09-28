package com.example.todoapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

  private Long id;

  @NotBlank(message = "Title is required")
  @Size(min = 3, max = 150, message = "Title must be between 3 and 150 characters")
  private String title;

  @NotBlank(message = "Description is required")
  @Size(min = 5, max = 1000, message = "Description must be between 5 and 1000 characters")
  private String description;

  @NotNull(message = "Status is required")
  private TaskStatus status = TaskStatus.TODO;

  @NotNull(message = "Priority is required")
  private TaskPriority priority = TaskPriority.MEDIUM;

  @NotNull(message = "Deadline is required")
  @FutureOrPresent(message = "Deadline must be today or in the future")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate deadline;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime createdAt;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime updatedAt;

  public Task() {}

  public Task(
      Long id,
      String title,
      String description,
      TaskStatus status,
      TaskPriority priority,
      LocalDate deadline,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.priority = priority;
    this.deadline = deadline;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public TaskPriority getPriority() {
    return priority;
  }

  public void setPriority(TaskPriority priority) {
    this.priority = priority;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return Objects.equals(id, task.id)
        && Objects.equals(title, task.title)
        && Objects.equals(description, task.description)
        && status == task.status
        && priority == task.priority
        && Objects.equals(deadline, task.deadline);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, status, priority, deadline);
  }
}
