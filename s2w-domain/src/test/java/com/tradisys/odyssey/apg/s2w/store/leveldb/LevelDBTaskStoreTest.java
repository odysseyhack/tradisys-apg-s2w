package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.io.Files;
import com.tradisys.odyssey.apg.s2w.domain.Organization;
import com.tradisys.odyssey.apg.s2w.domain.OrganizationStatus;
import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.domain.TaskStatus;
import com.tradisys.odyssey.apg.s2w.store.TaskStore;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LevelDBTaskStoreTest {

    protected static DB db;
    protected static TaskStore store;
    protected static Organization firstTestOrganization = createOrg("TestOrg", "some address", "123abc1", OrganizationStatus.VERIFIED);
    protected static Organization secondTestOrganization = createOrg("TestOrg#2", "some address", "123abc2", OrganizationStatus.DEACTIVATED);
    protected static Task firstTestTask = createTask("Task#1", "some test task1", 100d, 100d, firstTestOrganization, TaskStatus.OPEN);
    protected static Task secondTestTask = createTask("Task#2", "some test task2", 200d, 200d, secondTestOrganization, TaskStatus.DRAFT);

    private static Organization createOrg(String name, String address, String rsin, OrganizationStatus status) {
        Organization org = new Organization();
        org.setName(name);
        org.setAddress(address);
        org.setRsin(rsin);
        org.setStatus(status);
        return org;
    }

    private static Task createTask(String name, String description, Double tokenCost, Double votingPoints,
                                   Organization organization, TaskStatus taskStatus) {
        Task task = TaskTests.createTask(name, description, tokenCost, votingPoints, taskStatus);
        task.setOrganization(organization);
        return task;
    }

    @BeforeClass
    public static void beforeAll() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);

        File dbFile = Files.createTempDir();

        db = factory.open(dbFile, options);
        store = new LevelDBTaskStore(db);
    }

    @AfterClass
    public static void afterAll() throws IOException {
        db.close();
    }

    @Test
    public void t001_saveAssignment() {
        long firstTaskId = store.insert(firstTestTask);
        store.insert(secondTestTask);

        store.saveAssignment(firstTaskId, 1);
    }

    @Test
    public void t002_findAllAssignedTo() {
        List<Task> fromDB = store.findAllAssignedTo(1);

        Assert.assertEquals(fromDB.size(), 1);
        Assert.assertEquals(fromDB.contains(firstTestTask), true);
    }

    @Test
    public void t003_findAllCreatedBy() {
        List<Task> createdByFirst = store.findAllCreatedBy(firstTestOrganization);
        List<Task> createdBySecond = store.findAllCreatedBy(secondTestOrganization);

        Assert.assertEquals(createdByFirst.size(), 1);
        Assert.assertEquals(createdBySecond.size(), 1);

        Assert.assertEquals(createdByFirst.contains(firstTestTask), true);
        Assert.assertEquals(createdBySecond.contains(secondTestTask), true);
    }
}
