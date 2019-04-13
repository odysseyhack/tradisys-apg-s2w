package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.io.Files;
import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.store.CustomerStore;
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
        long firstCustomerId = store.insert(firstTestCustomer);
        long secondCustomerId = store.insert(secondTestCustomer);

        Assert.assertEquals(firstCustomerId, 1l);
        Assert.assertEquals(secondCustomerId, 2l);
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
        List<Customer> customersFromDB = store.findAll();
        Assert.assertEquals(
                customersFromDB
                        .contains(firstTestCustomer),
                true
        );
        Assert.assertEquals(
                customersFromDB
                        .contains(secondTestCustomer),
                true
        );
    }

    @Test
    public void t005_customerIdByBSN() {
        Optional<Long> maybeFirstCustomerId = store.customerIdByBSN(firstTestCustomer.getBsn());
        Optional<Long> maybeSecondCustomerId = store.customerIdByBSN(secondTestCustomer.getBsn());

        Assert.assertEquals(maybeFirstCustomerId.get(), new Long(1l));
        Assert.assertEquals(maybeSecondCustomerId.get(), new Long(2l));
    }

    @Test
    public void t006_deleteCustomerById() {
        store.deleteById(1);
        store.deleteById(2);

        List<Customer> customersFromDB = store.findAll();

        Assert.assertEquals(customersFromDB.isEmpty(), true);
    }
}
