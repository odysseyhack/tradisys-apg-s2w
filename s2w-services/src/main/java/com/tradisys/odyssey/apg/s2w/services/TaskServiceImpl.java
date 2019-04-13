package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.store.TaskStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TasksService {

    @Autowired
    private TaskStore tasksStore;

    @Override
    public Long createNewTask(Task task) {
        return tasksStore.insert(task);
    }

    @Override
    public void updateTask(Task task) {
        Optional<Task> taskEntity = tasksStore.findAll().stream()
                .filter(t -> t.getName().equals(task.getName()))
                .findFirst();
        taskEntity.ifPresent(t -> tasksStore.update(t));
    }

    @Override
    public void removeTask(Task task) {

    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public List<Task> getAllTasksByCustomer() {
        return null;
    }

    @Override
    public List<Task> getAllTasksByOrganization() {
        return null;
    }

    @Override
    public void assignTask(Customer customer) {

    }
}
