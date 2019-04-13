package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Task;

import java.util.List;

/**
 * Service to handle tasks mechanics
 */
public interface TasksService {

    Long createNewTask(Task task);

    void updateTask(Task task);

    void removeTask(Task task);

    List<Task> getAllTasks();

    List<Task> getAllTasksByCustomer();

    List<Task> getAllTasksByOrganization();

    void assignTask(Customer customer);

}
