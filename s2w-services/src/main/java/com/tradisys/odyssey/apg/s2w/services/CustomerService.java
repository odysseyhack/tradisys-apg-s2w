package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAllCustomers();

    Optional<Customer> findCustomerById(Long customerId);

    Optional<String> findCustomerAccountInfo(Long customerId);

    boolean ensureCustomerExists(Long customerId);
}
