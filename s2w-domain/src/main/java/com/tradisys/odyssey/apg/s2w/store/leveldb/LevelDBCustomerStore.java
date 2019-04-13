package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.store.CustomerStore;
import org.iq80.leveldb.DB;

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
}
