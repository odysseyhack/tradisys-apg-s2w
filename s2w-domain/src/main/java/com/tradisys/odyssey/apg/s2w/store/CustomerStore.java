package com.tradisys.odyssey.apg.s2w.store;

import com.tradisys.odyssey.apg.s2w.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerStore {
    int saveCustomer(Customer customer);
    void deleteCustomerById(int id);
    Optional<Customer> getCustomerById(int id);
    List<Customer> getAllCustomers();
}
