package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.store.EntityWithId;
import com.tradisys.odyssey.apg.s2w.store.leveldb.LevelDBTaskStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TasksService {

    @Autowired
    LevelDBTaskStore tasksStore;

    @Override
    public Integer createNewTask(Task task) {
        return tasksStore.insert(task);
    }

    @Override
    public void updateTask(Task task) {
        Optional<EntityWithId<Task>> taskEntity = tasksStore.findAll().stream()
                .filter(t -> t.getValue().getName().equals(task.getName()))
                .findFirst();
        taskEntity.ifPresent(taskEntityWithId -> tasksStore.update(taskEntityWithId.getId(), taskEntityWithId.getValue()));
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
