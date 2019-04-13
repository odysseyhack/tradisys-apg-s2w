package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.tradisys.odyssey.apg.s2w.domain.Organization;
import org.iq80.leveldb.DB;

public class LevelDBOrganizationStore extends BaseLevelDBStore<Organization> {
    @Override
    protected short getIdPrefix() {
        return Keys.OrganizationIdPrefix;
    }

    @Override
    protected short getTablePrefix() {
        return Keys.OrganizationPrefix;
    }

    @Override
    protected DB getDB() {
        return this.db;
    }

    private DB db;

    public LevelDBOrganizationStore(DB db) {
        this.db = db;
    }
}
