package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.services.dto.BaseRegDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Override
    public BaseRegDto register(BaseRegDto regInfo) {
        LOGGER.debug("REGISTERED");
        regInfo.setId(100L);
        return regInfo;
    }
}
