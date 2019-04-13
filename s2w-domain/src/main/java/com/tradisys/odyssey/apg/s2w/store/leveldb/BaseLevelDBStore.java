package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.primitives.Ints;
import com.tradisys.odyssey.apg.s2w.domain.BasicIdentity;
import com.tradisys.odyssey.apg.s2w.store.BaseStore;
import com.tradisys.odyssey.apg.s2w.store.EntityWithId;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.iq80.leveldb.DB;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class BaseLevelDBStore<T extends BasicIdentity> implements BaseStore<T> {
    protected abstract short getIdPrefix();
    protected abstract short getTablePrefix();

    protected abstract DB getDB();

    private int getLastId() {
        byte[] key = Keys.fromPrefix(getIdPrefix());
        return Optional.ofNullable(getDB().get(key)).map(Ints::fromByteArray).orElse(0);
    }

    private void setLastId(int id) {
        byte[] key = Keys.fromPrefix(getIdPrefix());
        getDB().put(key, Ints.toByteArray(id));
    }

    @Override
    public int insert(T t) {
        int lastId = getLastId();
        int newId = lastId + 1;

        setLastId(newId);

        byte[] keyBytes = Keys.fromPrefixAndId(getTablePrefix(), newId);
        byte[] valueBytes = SerializationUtils.serialize(t);

        getDB().put(keyBytes, valueBytes);

        return newId;
    }


    @Override
    public void update(int id, T t) {
        byte[] keyBytes = Keys.fromPrefixAndId(getTablePrefix(), id);
        byte[] valueBytes = SerializationUtils.serialize(t);

        getDB().put(keyBytes, valueBytes);
    }

    @Override
    public void deleteById(int id) {
        byte[] entityKey = Keys.fromPrefixAndId(getTablePrefix(), id);
        getDB().delete(entityKey);
    }

    @Override
    public Optional<T> findById(int id) {
        byte[] keyBytes = Keys.fromPrefixAndId(getTablePrefix(), id);

        return Optional.ofNullable(getDB().get(keyBytes))
                .map(SerializationUtils::deserialize);
    }

    @Override
    public List<EntityWithId<T>> findAll() {
        byte[] prefixBytes = Keys.fromPrefix(getTablePrefix());
        List<EntityWithId<T>> result = new ArrayList<>();

        try {
            Utils.iterateOverPrefix(getDB(), prefixBytes, entry -> {
                try {
                    byte[] keyBytes = entry.getKey();
                    byte[] valueBytes = entry.getValue();

                    T t = SerializationUtils.deserialize(valueBytes);
                    int id = Ints.fromByteArray(Arrays.copyOfRange(keyBytes, 2, 6));

                    result.add(new EntityWithId<>(t, id));
                } catch (SerializationException e) {}
            });
        }
        catch (IOException e) {
            return result;
        }
        return result;
    }
}
