package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.domain.BasicPrincipal;

public interface RegistrationService {
    BasicPrincipal register(BasicPrincipal regInfo);
}