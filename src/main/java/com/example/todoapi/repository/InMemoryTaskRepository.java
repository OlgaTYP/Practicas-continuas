package com.example.todoapi.repository;

import com.example.todoapi.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

  private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
  private final AtomicLong sequence = new AtomicLong(1L);

  @Override
  public List<Task> findAll() {
    return new ArrayList<>(tasks.values());
  }

  @Override
  public Optional<Task> findById(Long id) {
    return Optional.ofNullable(tasks.get(id));
  }

  @Override
  public Task save(Task task) {
    if (task.getId() == null) {
      Long newId = sequence.getAndIncrement();
      task.setId(newId);
    }
    tasks.put(task.getId(), task);
    return task;
  }

  @Override
  public void deleteById(Long id) {
    tasks.remove(id);
  }

  @Override
  public boolean existsById(Long id) {
    return tasks.containsKey(id);
  }
}
