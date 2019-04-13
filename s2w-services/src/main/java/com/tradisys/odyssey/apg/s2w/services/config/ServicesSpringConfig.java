package com.tradisys.odyssey.apg.s2w.services.config;

import com.fasterxml.jackson.databind.ObjectMapper;
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
}
