package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.primitives.Longs;
import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.store.CustomerStore;
import org.iq80.leveldb.DB;

import java.util.Optional;

public class LevelDBCustomerStore extends BaseLevelDBStore<Customer> implements CustomerStore {

    @Override
    protected short getIdPrefix() {
        return Keys.CustomerIdPrefix;
    }

    @Override
    protected short getTablePrefix() {
        return Keys.CustomerPrefix;
    }

    @Override
    protected DB getDB() {
        return this.db;
    }

    private DB db;

    public LevelDBCustomerStore(DB db) {
        this.db = db;
    }

    @Override
    public long insert(Customer customer) {
        long id = super.insert(customer);
        byte[] idByBsnKey = Keys.fromPrefixAndBytes(Keys.CustomerBSNPrefix, customer.getBsn().getBytes());
        db.put(idByBsnKey, Longs.toByteArray(id));
        return id;
    }

    @Override
    public Optional<Long> customerIdByBSN(String bsn) {
        byte[] keyBytes = Keys.fromPrefixAndBytes(Keys.CustomerBSNPrefix, bsn.getBytes());
        return Optional.ofNullable(db.get(keyBytes))
                .map(Longs::fromByteArray);
    }
}
