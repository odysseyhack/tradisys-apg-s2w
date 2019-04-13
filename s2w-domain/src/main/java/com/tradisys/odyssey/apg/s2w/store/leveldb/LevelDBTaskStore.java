package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.primitives.Longs;
import com.tradisys.odyssey.apg.s2w.domain.Organization;
import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.store.TaskStore;
import org.apache.commons.lang3.SerializationUtils;
import org.iq80.leveldb.DB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelDBTaskStore extends BaseLevelDBStore<Task> implements TaskStore {

    private DB db;

    public LevelDBTaskStore(DB db) {
        this.db = db;
    }

    @Override
    protected short getIdPrefix() {
        return Keys.TaskIdPrefix;
    }

    @Override
    protected short getTablePrefix() {
        return Keys.TaskPrefix;
    }

    @Override
    protected DB getDB() {
        return db;
    }

    @Override
    public void saveAssignment(long taskId, long userId) {
        byte[] keyBytes = Keys.fromPrefixAndId(Keys.TaskAssignmentPrefix, taskId);
        db.put(keyBytes, Longs.toByteArray(userId));
    }

    @Override
    public List<Task> findAllAssignedTo(long userId) {
        byte[] prefixBytes = Keys.fromPrefix(Keys.TaskAssignmentPrefix);

        List<Task> result = new ArrayList<>();

        try {
            Utils.iterateOverPrefix(db, prefixBytes, entry -> {
                byte[] keyBytes = entry.getKey();
                long assignedTo = Longs.fromByteArray(entry.getValue());

                if (assignedTo == userId) {
                    byte[] taskIdBytes = Arrays.copyOfRange(keyBytes, 2, 10);
                    long taskId = Longs.fromByteArray(taskIdBytes);

                    findById(taskId)
                            .ifPresent(result::add);
                }
            });
        } catch (IOException e) {
            return result;
        }
        return result;
    }

    @Override
    public List<Task> findAllCreatedBy(Organization org) {
        byte[] prefix = Keys.fromPrefix(Keys.TaskPrefix);
        List<Task> result = new ArrayList<>();

        try {
            Utils.iterateOverPrefix(db, prefix, entry -> {
                Task task = SerializationUtils.deserialize(entry.getValue());

                if (task.getCreatedBy().equals(org)) {
                    result.add(task);
                }
            });
        } catch (IOException e) {
            return result;
        }
        return result;
    }
}
