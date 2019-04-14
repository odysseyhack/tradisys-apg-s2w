package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.domain.TaskStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class TaskTests {

    @Test
    public void testTasks() {
        Task task = createTask("name", "description",123.0, 123.0, TaskStatus.CLOSED);
        assertEquals(task.getName(),"name");
        assertEquals(task.getDescription(),"description");
        assertEquals(task.getStatus(),TaskStatus.CLOSED);
        assertNotNull(task);
    }

    public static Task createTask(String name, String description, Double tokenCost, Double votingPoints, TaskStatus taskStatus) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setTokenCost(tokenCost);
        task.setVotingPoints(votingPoints);
        task.setStatus(taskStatus);
        return task;
    }

}
