package com.tradisys.odyssey.apg.s2w.services.impl;

import com.tradisys.odyssey.apg.s2w.domain.Organization;
import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.services.TasksService;
import com.tradisys.odyssey.apg.s2w.store.CustomerStore;
import com.tradisys.odyssey.apg.s2w.store.OrganizationStore;
import com.tradisys.odyssey.apg.s2w.store.TaskStore;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TasksService {

    @Autowired
    TaskStore tasksStore;

    @Autowired
    OrganizationStore organizationStore;

    @Autowired
    CustomerStore customerStore;

    @Override
    public Task createNewTask(Task task) {
        long taskId = tasksStore.insert(task);
        Task justAddedTask = tasksStore.findById(taskId).get();
        justAddedTask.setId(taskId);
        return justAddedTask;
    }

    @Override
    public void updateTask(Task task) {
        Optional<Task> taskFound = findTask(task.getId().toString());
        taskFound.ifPresent(taskEntityWithId -> tasksStore.update(task));
    }

    @Override
    public void removeTask(String taskId) {
        Optional<Task> task = findTask(taskId);
        task.ifPresent(taskEntityWithId -> tasksStore.deleteById(taskEntityWithId.getId()));
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasksStore.findAll());
    }

    @Override
    public List<Task> getAllTasksByCustomer(Long customerId) {
        return tasksStore.findAllAssignedTo(customerId);
    }

    @Override
    public List<Task> getAllTasksByOrganization(Long organizationId) {
        List<Task> organizationTasks = null;
        Optional<Organization> organizationOptional = organizationStore.findById(organizationId);
        if (organizationOptional.isPresent()) {
            organizationTasks = tasksStore.findAllCreatedBy(organizationOptional.get());
        }
        return organizationTasks;
    }

    @Override
    public boolean assignTask(Long taskId, Long customerId) {

        boolean customerExists = customerStore.findById(customerId).isPresent();
        boolean taskExists = tasksStore.findById(taskId).isPresent();

        if (customerExists && taskExists) {
            tasksStore.saveAssignment(taskId, customerId);
            return true;
        } else {
            return false;
        }
    }

    private Optional<Task> findTask(String taskId) {
        Optional<Task> taskOptional = tasksStore.findById(Integer.valueOf(taskId));
        Task task = null;
        if(taskOptional.isPresent()){
            task = taskOptional.get();
        }
        Task finalTask = task;
        Optional<Task> taskEntity = tasksStore.findAll().stream()
                .filter(t -> t.getName().equals(finalTask.getName()))
                .findFirst();
        return taskEntity;
    }
}