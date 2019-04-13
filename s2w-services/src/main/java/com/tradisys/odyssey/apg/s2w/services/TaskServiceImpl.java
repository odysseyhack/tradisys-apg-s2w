package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Organization;
import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.store.CustomerStore;
import com.tradisys.odyssey.apg.s2w.store.EntityWithId;
import com.tradisys.odyssey.apg.s2w.store.OrganizationStore;
import com.tradisys.odyssey.apg.s2w.store.TaskStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TasksService {

    @Autowired
    TaskStore tasksStore;

    @Autowired
    OrganizationStore organizationStore;

    @Autowired
    CustomerStore customerStore;

    @Override
    public Task createNewTask(Task task) {
        int taskId = tasksStore.insert(task);
        Task justAddedTask = tasksStore.findById(taskId).get();
        justAddedTask.setId((long) taskId);
        return justAddedTask;
    }

    @Override
    public void updateTask(String taskId) {
        Optional<EntityWithId<Task>> task = findTask(taskId);
        task.ifPresent(taskEntityWithId -> tasksStore.update(taskEntityWithId.getId(), taskEntityWithId.getValue()));
    }

    @Override
    public void removeTask(String taskId) {
        Optional<EntityWithId<Task>> task = findTask(taskId);
        task.ifPresent(taskEntityWithId -> tasksStore.deleteById(taskEntityWithId.getId()));
    }

    @Override
    public List<Task> getAllTasks() {
        return tasksStore.findAll().stream().map(EntityWithId::getValue).collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksByCustomer(Integer customerId) {
        return tasksStore.findAllAssignedTo(customerId);
    }

    @Override
    public List<Task> getAllTasksByOrganization(Integer organizationId) {
        List<Task> organizationTasks = null;
        Optional<Organization> organizationOptional = organizationStore.findById(organizationId);
        if (organizationOptional.isPresent()) {
            organizationTasks = tasksStore.findAllCreatedBy(organizationOptional.get());
        }
        return organizationTasks;
    }

    @Override
    public void assignTask(Integer taskId, Integer customerId) {
        Optional<Customer> customerOptional = customerStore.findById(customerId);
        Customer customer = null;
        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
        }
        Optional<Task> taskOptional = tasksStore.findById(taskId);
        Task task = null;
        if (taskOptional.isPresent()) {
            task = taskOptional.get();
            customer.addTask(task);
        }
        customerStore.update(customer.getId().intValue(), customer);
        task.setCustomer(customer);
        tasksStore.update(task.getId().intValue(), task);
    }

    private Optional<EntityWithId<Task>> findTask(String taskId) {
        Optional<Task> taskOptional = tasksStore.findById(Integer.valueOf(taskId));
        Task task = null;
        if(taskOptional.isPresent()){
            task = taskOptional.get();
        }
        Task finalTask = task;
        Optional<EntityWithId<Task>> taskEntity = tasksStore.findAll().stream()
                .filter(t -> t.getValue().getName().equals(finalTask.getName()))
                .findFirst();
        return taskEntity;
    }
}