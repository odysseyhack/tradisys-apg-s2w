package com.tradisys.odyssey.apg.s2w.controller;

import com.tradisys.odyssey.apg.s2w.domain.BasicPrincipal;
import com.tradisys.odyssey.apg.s2w.domain.Role;
import com.tradisys.odyssey.apg.s2w.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/{role}/register")
    public ResponseEntity<?> register(@PathVariable Role role, @RequestBody BasicPrincipal principal) {
        BasicPrincipal princip = registrationService.register(principal);
        return new ResponseEntity<>(princip, HttpStatus.OK);
    }

    @RequestMapping("/status")
    public String status() {
        return "ONLINE";
    }
}
