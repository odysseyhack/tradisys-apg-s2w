package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.domain.Customer;

public interface RegistrationService {
    Customer register(Customer regInfo);
}