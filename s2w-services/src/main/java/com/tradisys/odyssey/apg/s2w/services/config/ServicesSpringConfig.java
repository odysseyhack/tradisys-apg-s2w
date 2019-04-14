package com.tradisys.odyssey.apg.s2w.services.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradisys.odyssey.apg.s2w.services.CustomerService;
import com.tradisys.odyssey.apg.s2w.services.OrganizationService;
import com.tradisys.odyssey.apg.s2w.services.RegistrationService;
import com.tradisys.odyssey.apg.s2w.services.TasksService;
import com.tradisys.odyssey.apg.s2w.services.impl.CustomerServiceImpl;
import com.tradisys.odyssey.apg.s2w.services.impl.OrganizationServiceImpl;
import com.tradisys.odyssey.apg.s2w.services.impl.RegistrationServiceImpl;
import com.tradisys.odyssey.apg.s2w.services.impl.TaskServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.tradisys.odyssey"})
public class ServicesSpringConfig {

    @Bean
    public ObjectMapper jakson() {
        ObjectMapper jaskson = new ObjectMapper();
        return jaskson;
    }

    @Bean
    public TasksService tasksService(){
        return new TaskServiceImpl();
    }

    @Bean
    public CustomerService customerService(){ return new CustomerServiceImpl(); }

    @Bean
    public OrganizationService organizationService(){ return new OrganizationServiceImpl(); }
}
