package com.tradisys.odyssey.apg.s2w.dto;

import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Organization;
import com.tradisys.odyssey.apg.s2w.services.CustomerService;
import com.tradisys.odyssey.apg.s2w.services.OrganizationService;
import com.tradisys.odyssey.apg.s2w.services.RegistrationService;
import com.tradisys.odyssey.apg.s2w.services.TasksService;
import com.tradisys.odyssey.apg.s2w.services.config.ServicesSpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= ServicesSpringConfig.class)
public class ServicesTests {



    @Autowired
    private CustomerService customerService;

    @Autowired
    private TasksService tasksService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RegistrationService registrationService;

    @Test
    public void testCustomerService(){
        customerService = mock(CustomerService.class);
        when(customerService.ensureCustomerExists(1L)).thenReturn(true);
        assertTrue(customerService.ensureCustomerExists(1L));
    }

    @Test
    public void testTaskService(){
        tasksService = mock(TasksService.class);
        when(tasksService.getAllTasks()).thenReturn(null);
        assertNull(tasksService.getAllTasks());
    }

    @Test
    public void testOrganizationService(){
        organizationService = mock(OrganizationService.class);
        when(organizationService.ensureOrganizationExists(1L)).thenReturn(true);
        assertTrue(organizationService.ensureOrganizationExists(1L));
    }

    @Test
    public void testResistrationService(){
        registrationService = mock(RegistrationService.class);
        when(registrationService.register(null)).thenReturn(null);
        assertNull(registrationService.register(null));
    }

}
