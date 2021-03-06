package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.domain.Task;
import java.util.List;

/**
 * Service to handle tasks mechanics
 */
public interface TasksService {

    Task createNewTask(Task task);

    void updateTask(Task task);

    void removeTask(String taskId);

    List<Task> getAllTasks();

    List<Task> getAllTasksByCustomer(Long customerId);

    List<Task> getAllTasksByOrganization(Long organizationId);

    boolean assignTask(Long taskId, Long customerId);

}
