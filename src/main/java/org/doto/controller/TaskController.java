package org.doto.controller;

import org.doto.dto.TaskDto;
import org.doto.entity.Task;
import org.doto.entity.User;
import org.doto.service.TaskService;
import org.doto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable Integer id) {
        Task task = taskService.findById(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Task> saveTask(@RequestBody TaskDto taskDto){

        if(taskDto.getId() != null){
            Task task = taskService.findById(taskDto.getId());
            if(task != null){
                User user = userService.findById(taskDto.getUserId());
                if(user != null){
                    return getTaskResponseEntity(taskDto, task, user);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            User user = userService.findById(taskDto.getUserId());
            if(user != null){
                return getTaskResponseEntity(taskDto, new Task(), user);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    private ResponseEntity<Task> getTaskResponseEntity(TaskDto taskDto, Task task, User user) {
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCreationDate(taskDto.getCreationDate());
        task.setDeadline(taskDto.getDeadline());
        task.setStatus(taskDto.getStatus());
        task.setUser(user);

        return new ResponseEntity<>(taskService.save(task), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
