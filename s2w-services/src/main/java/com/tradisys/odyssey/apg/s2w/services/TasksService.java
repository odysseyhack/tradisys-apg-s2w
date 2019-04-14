package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Task;

import java.util.List;

/**
 * Service to handle tasks mechanics
 */
public interface TasksService {

    Task createNewTask(Task task);

    void updateTask(String taskId);

    void removeTask(String taskId);

    List<Task> getAllTasks();

    List<Task> getAllTasksByCustomer(Long customerId);

    List<Task> getAllTasksByOrganization(Long organizationId);

    void assignTask(Long taskId, Long customerId);

}
