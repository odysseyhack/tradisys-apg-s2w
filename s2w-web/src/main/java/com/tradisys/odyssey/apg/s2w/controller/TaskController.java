package com.tradisys.odyssey.apg.s2w.controller;

import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/task")
@RestController
public class TaskController {

    @Autowired
    private TasksService tasksService;

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@PathVariable Task task) {
         tasksService.createNewTask(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateTask(@PathVariable Task task) {
        tasksService.updateTask(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeTask(@PathVariable Task task) {
        tasksService.removeTask(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignTask(@PathVariable String taskId,  @PathVariable String customerId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
