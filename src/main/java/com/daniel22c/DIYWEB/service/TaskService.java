package com.daniel22c.DIYWEB.service;

import com.daniel22c.DIYWEB.model.Task;

public interface TaskService {
    Iterable<Task> findAll();
    Task findOne(Long id);
    void toggleComplete(Long id);
    void save(Task task);
}
