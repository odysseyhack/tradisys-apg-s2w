package com.tradisys.odyssey.apg.s2w.controller;

import com.tradisys.odyssey.apg.s2w.domain.Organization;
import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.services.OrganizationService;
import com.tradisys.odyssey.apg.s2w.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    TasksService tasksService;

    @GetMapping("/orgs")
    public ResponseEntity<?> findAllOrganizations() {
        List<Organization> organizations = organizationService.findAllOrganizations();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/orgs/{id}")
    public ResponseEntity<?> findOrganizationById(@PathVariable Long organizationId) {
        Optional<Organization> maybeOrganization = organizationService.findOrganizationById(organizationId);

        return maybeOrganization
                .<ResponseEntity<?>>map(org -> new ResponseEntity<>(org, HttpStatus.OK))
                .orElseGet(() -> organizationNotFoundError(organizationId));
    }

    @GetMapping("/orgs/{id}/tasks")
    public ResponseEntity<?> findTasksCreatedByOrganization(@PathVariable Long organizationId) {
        boolean organizationExists = organizationService
                .ensureOrganizationExists(organizationId);

        if (organizationExists) {
            List<Task> tasks = tasksService.getAllTasksByOrganization(organizationId);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return organizationNotFoundError(organizationId);
        }
    }

    @PostMapping("orgs/{id}/tasks")
    public ResponseEntity<?> createTaskForOrganization(@PathVariable Long organizationId, @RequestBody Task task) {
        Optional<Organization> maybeOrganization = organizationService
                .findOrganizationById(organizationId);

        return maybeOrganization
                .<ResponseEntity<?>>map(org -> {
                    task.setOrganization(org);
                    tasksService.createNewTask(task);
                    return new ResponseEntity<>(task, HttpStatus.OK);
                }).orElseGet(() -> organizationNotFoundError(organizationId));
    }

    private ResponseEntity<String> organizationNotFoundError(Long organizationId) {
        String errorMessage = String.format("Organization with id - %d not found", organizationId);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
