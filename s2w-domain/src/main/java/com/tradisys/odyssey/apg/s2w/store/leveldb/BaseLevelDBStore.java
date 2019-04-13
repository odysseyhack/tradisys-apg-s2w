package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.primitives.Ints;
import com.tradisys.odyssey.apg.s2w.store.BaseStore;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.iq80.leveldb.DB;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseLevelDBStore<T extends Serializable> implements BaseStore<T> {
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
    public List<T> findAll() {
        byte[] prefixBytes = Keys.fromPrefix(getTablePrefix());
        List<T> result = new ArrayList<>();

        try {
            Utils.iterateOverPrefix(getDB(), prefixBytes, entry -> {
                try {
                    byte[] valueBytes = entry.getValue();
                    T t = SerializationUtils.deserialize(valueBytes);
                    result.add(t);
                } catch (SerializationException e) {}
            });
        }
        catch (IOException e) {
            return result;
        }
        return result;
    }
}
