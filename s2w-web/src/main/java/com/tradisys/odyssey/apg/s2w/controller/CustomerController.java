package com.tradisys.odyssey.apg.s2w.controller;

import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Task;
import com.tradisys.odyssey.apg.s2w.keychain.KeychainProvider;
import com.tradisys.odyssey.apg.s2w.services.CustomerService;
import com.tradisys.odyssey.apg.s2w.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    TasksService tasksService;

    @Autowired
    KeychainProvider keychainProvider;

    @GetMapping("/customers")
    public ResponseEntity<?> findAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long customerId) {
        Optional<Customer> maybeCustomer = customerService.findCustomerById(customerId);

        if (maybeCustomer.isPresent()) {
            return new ResponseEntity<>(maybeCustomer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers/{id}/tasks")
    public ResponseEntity<?> findAssignedTasks(@PathVariable Long customerId) {
        boolean customerExists = customerService.ensureCustomerExists(customerId);
        if (customerExists) {
            List<Task> assignedTasks = tasksService.getAllTasksByCustomer(customerId);
            return new ResponseEntity<>(assignedTasks, HttpStatus.OK);
        } else {
            String errorMessage = String.format("Customer with id - {} not found", customerId);
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers/{id}/account")
    public ResponseEntity<?> findCustomerAccountInfo(@PathVariable Long customerId) {
        Optional<String> maybeCustomerSeed = customerService.findCustomerAccountInfo(customerId);

        return maybeCustomerSeed
                .map(seed -> new ResponseEntity<>(seed, HttpStatus.OK))
                .orElse(customerNotFoundError(customerId));
    }

    protected ResponseEntity<String> customerNotFoundError(Long customerId) {
        String errorMessage = String.format("Customer with id - {} not found", customerId);
        return new ResponseEntity<String>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
