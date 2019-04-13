package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.io.Files;
import com.tradisys.odyssey.apg.s2w.store.BaseStore;
import com.tradisys.odyssey.apg.s2w.store.EntityWithId;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BaseLevelDBStoreTest {

    protected static DB db;
    protected static BaseStore<String> store;

    @BeforeClass
    public static void beforeAll() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);

        File dbFile = Files.createTempDir();

        db = factory.open(dbFile, options);
        store = new BaseLevelDBStore<String>() {
            @Override
            protected short getIdPrefix() {
                return 1;
            }

            @Override
            protected short getTablePrefix() {
                return 2;
            }

            @Override
            protected DB getDB() {
                return db;
            }
        };
    }

    @AfterClass
    public static void afterAll() throws IOException {
        db.close();
    }

    @Test
    public void t001_insert() {
        int id = store.insert("TestString");
        Assert.assertEquals(id, 1);
    }

    @Test
    public void t005_deleteById() {
        store.deleteById(1);
        List<EntityWithId<String>> fromDB = store.findAll();
        Assert.assertEquals(fromDB.size(), 0);
    }

    @Test
    public void t002_findById() {
        String fromDB = store.findById(1).get();

        Assert.assertEquals(fromDB, "TestString");
    }

    @Test
    public void t004_findAll() {
        List<EntityWithId<String>> fromDB = store.findAll();

        Assert.assertEquals(fromDB.size(), 1);
        Assert.assertEquals(fromDB.get(0), new EntityWithId<>("UpdatedString", 1));
    }

    @Test
    public void t003_update() {
        store.update(1, "UpdatedString");
    }
}
