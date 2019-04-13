package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.io.Files;
import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.store.BaseStore;
import com.tradisys.odyssey.apg.s2w.store.EntityWithId;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BaseLevelDBStoreTest {

    protected static DB db;
    protected static BaseStore store;

    @BeforeClass
    public static void beforeAll() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);

        File dbFile = Files.createTempDir();

        db = factory.open(dbFile, options);
        store = new BaseLevelDBStore<Customer>() {
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
        int id = store.insert(createDefaultCustomer());
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
        Customer fromDB = (Customer) store.findById(1).get();

        Assert.assertEquals("Tradisys", fromDB.getFirstName());
    }

    @Test
    public void t004_findAll() {
        List<EntityWithId<Customer>> fromDB = store.findAll();

        Assert.assertEquals(fromDB.size(), 1);
        Assert.assertEquals(fromDB.get(0), new EntityWithId<>(createDefaultCustomer(), 1));
    }

    @Test
    public void t003_update() {
        store.update(1, createDefaultCustomer());
    }

    private Customer createDefaultCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Tradisys");
        customer.setSecondName("APG");
        customer.setAddress("Address");
        customer.setBsn("BSN");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1976, 10, 10, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        customer.setBirth(calendar.getTime());
        return customer;
    }
}
