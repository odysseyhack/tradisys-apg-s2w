package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.primitives.Ints;
import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.store.CustomerStore;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.*;

public class LevelDBCustomerStore implements CustomerStore {

    private DB db;

    public LevelDBCustomerStore(DB db) {
        this.db = db;
    }

    private int getLastCustomerId() {
        byte[] key = Keys.fromPrefix(Keys.CustomerIdPrefix);
        return Optional.ofNullable(db.get(key)).map(Ints::fromByteArray).orElse(0);
    }

    @Override
    public int saveCustomer(Customer customer) {
        int lastId = getLastCustomerId();
        int newCustomerId = lastId + 1;

        byte[] keyBytes = Keys.fromPrefixAndId(Keys.CustomerPrefix, newCustomerId);
        byte[] customerBytes = SerializationUtils.serialize(customer);

        db.put(Keys.fromPrefix(Keys.CustomerIdPrefix), Ints.toByteArray(newCustomerId));
        db.put(keyBytes, customerBytes);

        return newCustomerId;
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        byte[] customerKey = Keys.fromPrefixAndId(Keys.CustomerPrefix, id);

        return Optional.ofNullable(db.get(customerKey))
                .map(SerializationUtils::deserialize);
    }

    @Override
    public List<Customer> getAllCustomers() {
        byte[] prefixBytes = Keys.fromPrefix(Keys.CustomerPrefix);
        List<Customer> customers = new ArrayList<>();

        try {
            Utils.iterateOverPrefix(db, prefixBytes, entry -> {
                try {
                    byte[] customerBytes = entry.getValue();
                    Customer customer = SerializationUtils.deserialize(customerBytes);
                    customers.add(customer);
                } catch (SerializationException e) {}
            });
        }
        catch (IOException e) {
            return customers;
        }
        return customers;
    }

    @Override
    public void deleteCustomerById(int id) {
        byte[] customerKey = Keys.fromPrefixAndId(Keys.CustomerPrefix, id);
        db.delete(customerKey);
    }
}
