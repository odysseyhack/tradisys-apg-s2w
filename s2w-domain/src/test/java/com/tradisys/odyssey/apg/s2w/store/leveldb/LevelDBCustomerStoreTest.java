package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.io.Files;
import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.store.CustomerStore;
import com.tradisys.odyssey.apg.s2w.store.EntityWithId;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LevelDBCustomerStoreTest {

    protected static DB db;
    protected static CustomerStore store;

    protected static Customer firstTestCustomer = new Customer(
            "John",
            "Doe",
            "bsn123",
            "ulitsa Pushkina, dom Kolotushkina",
            new Date(System.currentTimeMillis())
    );

    protected static Customer secondTestCustomer = new Customer(
            "Jane",
            "Roe",
            "bsn321",
            "ulitsa Pushkina, dom Kolotushkina",
            new Date(System.currentTimeMillis())
    );


    @BeforeClass
    public static void beforeAll() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);

        File dbFile = Files.createTempDir();

        db = factory.open(dbFile, options);
        store = new LevelDBCustomerStore(db);
    }

    @AfterClass
    public static void afterAll() throws IOException {
        db.close();
    }

    @Test
    public void t001_saveCustomer() {
        int firstCustomerId = store.insert(firstTestCustomer);
        int secondCustomerId = store.insert(secondTestCustomer);

        Assert.assertEquals(firstCustomerId, 1);
        Assert.assertEquals(secondCustomerId, 2);
    }

    @Test
    public void t002_getCustomerById() {
        Optional<Customer> firstCustomerFromDB = store.findById(1);
        Optional<Customer> secondCustomerFromDB = store.findById(2);

        Assert.assertEquals(firstCustomerFromDB.get(), firstTestCustomer);
        Assert.assertEquals(secondCustomerFromDB.get(), secondTestCustomer);
    }

    @Test
    public void t003_getAllCustomers() {
        List<EntityWithId<Customer>> customersFromDB = store.findAll();
        Assert.assertEquals(
                customersFromDB
                        .contains(new EntityWithId<>(firstTestCustomer, 1)),
                true
        );
        Assert.assertEquals(
                customersFromDB
                        .contains(new EntityWithId<>(secondTestCustomer, 2)),
                true
        );
    }

    @Test
    public void t005_customerIdByBSN() {
        Optional<Integer> maybeFirstCustomerId = store.customerIdByBSN(firstTestCustomer.getBsn());
        Optional<Integer> maybeSecondCustomerId = store.customerIdByBSN(secondTestCustomer.getBsn());

        Assert.assertEquals(maybeFirstCustomerId.get(), new Integer(1));
        Assert.assertEquals(maybeSecondCustomerId.get(), new Integer(2));
    }

    @Test
    public void t006_deleteCustomerById() {
        store.deleteById(1);
        store.deleteById(2);

        List<EntityWithId<Customer>> customersFromDB = store.findAll();

        Assert.assertEquals(customersFromDB.isEmpty(), true);
    }
}
