package com.tradisys.odyssey.apg.s2w.controller;

import com.tradisys.odyssey.apg.s2w.domain.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class RegistrationController {

    @PostMapping("/{role}/register")
    public ResponseEntity<?> register(@PathVariable Role role) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/status")
    public String status() {
        return "ONLINE";
    }
}
