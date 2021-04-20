package org.doto.service.impl;

import org.doto.entity.Task;
import org.doto.entity.User;
import org.doto.repository.TaskRepository;
import org.doto.repository.UserRepository;
import org.doto.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findByUserId(Integer id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(taskRepository::findByUser).orElse(null);
    }

    @Override
    public Task findById(Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void delete(Integer id) {

        taskRepository.deleteById(id);
    }
}
