package com.tradisys.odyssey.apg.s2w.controller;

import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class TaskController {

    @Autowired
    private TasksService tasksService;

    @GetMapping("tasks")
    public ResponseEntity<?> allTasks() {
        List<Task> tasks = tasksService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("tasks/add")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        tasksService.createNewTask(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<?> removeTask(@PathVariable String id) {
        tasksService.removeTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/tasks/assign/{taskId}/{customerId}")
    public ResponseEntity<?> assignTask(@PathVariable Long taskId, @PathVariable Long customerId) {
        boolean success = tasksService.assignTask(taskId, customerId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
