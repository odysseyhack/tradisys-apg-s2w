package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.primitives.Longs;
import com.tradisys.odyssey.apg.s2w.domain.BasicIdentity;
import com.tradisys.odyssey.apg.s2w.store.BaseStore;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.iq80.leveldb.DB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseLevelDBStore<T extends BasicIdentity> implements BaseStore<T> {
    protected abstract short getIdPrefix();
    protected abstract short getTablePrefix();

    protected abstract DB getDB();

    private long getLastId() {
        byte[] key = Keys.fromPrefix(getIdPrefix());
        return Optional.ofNullable(getDB().get(key)).map(Longs::fromByteArray).orElse(0l);
    }

    private void setLastId(long id) {
        byte[] key = Keys.fromPrefix(getIdPrefix());
        getDB().put(key, Longs.toByteArray(id));
    }

    @Override
    public long insert(T t) {
        long lastId = getLastId();
        long newId = lastId + 1;

        setLastId(newId);

        t.setId(newId);

        byte[] keyBytes = Keys.fromPrefixAndId(getTablePrefix(), newId);
        byte[] valueBytes = SerializationUtils.serialize(t);

        getDB().put(keyBytes, valueBytes);

        return newId;
    }


    @Override
    public void update(T t) {
        byte[] keyBytes = Keys.fromPrefixAndId(getTablePrefix(), t.getId());
        byte[] valueBytes = SerializationUtils.serialize(t);

        getDB().put(keyBytes, valueBytes);
    }

    @Override
    public void deleteById(long id) {
        byte[] entityKey = Keys.fromPrefixAndId(getTablePrefix(), id);
        getDB().delete(entityKey);
    }

    @Override
    public Optional<T> findById(long id) {
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
