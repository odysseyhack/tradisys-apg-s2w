package com.tradisys.odyssey.apg.s2w.dto;


import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Role;
import com.tradisys.odyssey.apg.s2w.services.CustomerService;
import com.tradisys.odyssey.apg.s2w.services.RegistrationService;
import com.tradisys.odyssey.apg.s2w.services.config.ServicesSpringConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServicesSpringConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceImplTest {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    CustomerService customerService;

    private Customer testCustomer;

    @Before
    public void beforeEach() {
        testCustomer = new Customer();
        testCustomer.setFirstName("First");
        testCustomer.setSecondName("Second");
        testCustomer.setBsn("123 132 312");
        testCustomer.setAddress("address");
        testCustomer.setRole(Role.CUSTOMER);
        testCustomer.setId(1l);
        testCustomer.setBirth(new Date(System.currentTimeMillis()));
    }

    @Test
    public void t001_register() {
        registrationService.register(testCustomer);
    }

    @Test
    public void t002_findAll() {
        List<Customer> customers = customerService.findAllCustomers();

        Assert.assertEquals(customers.size(), 1);
    }

}
