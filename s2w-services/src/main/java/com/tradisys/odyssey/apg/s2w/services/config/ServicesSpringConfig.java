package com.tradisys.odyssey.apg.s2w.services.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradisys.odyssey.apg.s2w.services.TaskServiceImpl;
import com.tradisys.odyssey.apg.s2w.services.TasksService;
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
}
