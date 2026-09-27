package com.example.todoapi.repository;

import com.example.todoapi.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task save(Task task);

    void deleteById(Long id);

    boolean existsById(Long id);
}
