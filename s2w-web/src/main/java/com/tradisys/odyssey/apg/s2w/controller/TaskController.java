package com.tradisys.odyssey.apg.s2w.controller;

import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class TaskController {

    @Autowired
    private TasksService tasksService;

    @PostMapping("tasks/add")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
         tasksService.createNewTask(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable String id) {
        tasksService.updateTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<?> removeTask(@PathVariable String id) {
        tasksService.removeTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/assign/{taskId}/{customerId}")
    public ResponseEntity<?> assignTask(@PathVariable String taskId,  @PathVariable String customerId) {
        tasksService.assignTask(Integer.valueOf(taskId),Integer.valueOf(customerId));
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
