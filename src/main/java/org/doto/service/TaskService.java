package org.doto.service;

import org.doto.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();

    List<Task> findByUserId(Integer id);

    Task findById(Integer id);

    Task save(Task task);

    void delete(Integer id);
}
