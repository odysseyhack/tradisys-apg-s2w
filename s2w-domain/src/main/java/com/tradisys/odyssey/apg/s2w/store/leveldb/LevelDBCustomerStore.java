package com.tradisys.odyssey.apg.s2w.store.leveldb;

import com.google.common.primitives.Ints;
import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.store.CustomerStore;
import org.apache.commons.lang3.SerializationUtils;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LevelDBCustomerStore implements CustomerStore {

    private DB db;

    public LevelDBCustomerStore(DB db) {
        this.db = db;
    }

    private int getLastCustomerId() {
        byte[] key = Keys.fromPrefix(Keys.CustomerIdPrefix);
        return Ints.fromByteArray(db.get(key));
    }

    @Override
    public int saveCustomer(Customer customer) {
        int lastId = getLastCustomerId();
        int newCustomerId = lastId + 1;

        byte[] keyBytes = Keys.fromPrefixAndId(Keys.CustomerPrefix, newCustomerId);
        byte[] customerBytes = SerializationUtils.serialize(customer);

        db.put(keyBytes, customerBytes);

        return newCustomerId;
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        byte[] customerKey = Keys.fromPrefixAndId(Keys.CustomerPrefix, id);

        return Optional.of(db.get(customerKey))
                .map(SerializationUtils::deserialize);
    }

    @Override
    public List<Customer> getAllCustomers() {
        byte[] prefixBytes = Keys.fromPrefix(Keys.CustomerIdPrefix);
        List<Customer> customers = new ArrayList<>();

        try (DBIterator iter = db.iterator()) {
            for (iter.seek(prefixBytes); iter.hasNext(); iter.next()) {
                Optional<Customer> maybeCustomer = Optional.of(iter.peekNext().getValue())
                        .map(SerializationUtils::deserialize);

                maybeCustomer.ifPresent(customers::add);
            }
        } catch (IOException e) {
        }

        return customers;
    }

    @Override
    public void deleteCustomerById(int id) {
        byte[] customerKey = Keys.fromPrefixAndId(Keys.CustomerPrefix, id);
        db.delete(customerKey);
    }
}
