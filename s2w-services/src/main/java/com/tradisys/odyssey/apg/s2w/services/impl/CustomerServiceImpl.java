package com.tradisys.odyssey.apg.s2w.services.impl;

import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.keychain.KeychainProvider;
import com.tradisys.odyssey.apg.s2w.services.CustomerService;
import com.tradisys.odyssey.apg.s2w.store.CustomerStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerStore customerStore;

    @Autowired
    KeychainProvider keychainProvider;

    @Override
    public List<Customer> findAllCustomers() {
        return customerStore.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(Long customerId) {
        return customerStore.findById(customerId);
    }

    @Override
    public boolean ensureCustomerExists(Long customerId) {
        return customerStore.findById(customerId).isPresent();
    }

    @Override
    public Optional<String> findCustomerAccountInfo(Long customerId) {
        String maybeSeed = keychainProvider.getSeedById(customerId.toString());
        return Optional.ofNullable(maybeSeed);
    }
}
