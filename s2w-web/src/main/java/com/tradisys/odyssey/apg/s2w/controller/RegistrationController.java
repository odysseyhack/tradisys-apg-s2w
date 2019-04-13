package com.tradisys.odyssey.apg.s2w.controller;

import com.tradisys.odyssey.apg.s2w.domain.Role;
import com.tradisys.odyssey.apg.s2w.services.RegistrationService;
import com.tradisys.odyssey.apg.s2w.services.dto.BaseRegDto;
import com.tradisys.odyssey.apg.s2w.services.dto.CustomerRegDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/{role}/register")
    public ResponseEntity<?> register(@PathVariable Role role) {
        CustomerRegDto customerRegDto = new CustomerRegDto();
        BaseRegDto customer = registrationService.register(customerRegDto);
        return new ResponseEntity<>(customerRegDto, HttpStatus.OK);
    }

    @RequestMapping("/status")
    public String status() {
        return "ONLINE";
    }
}
