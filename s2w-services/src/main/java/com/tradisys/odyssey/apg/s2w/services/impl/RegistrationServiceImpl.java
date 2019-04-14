package com.tradisys.odyssey.apg.s2w.services.impl;

import com.tradisys.odyssey.apg.s2w.domain.BasicPrincipal;
import com.tradisys.odyssey.apg.s2w.services.RegistrationService;
import com.tradisys.odyssey.apg.s2w.store.BaseStore;
import com.tradisys.odyssey.apg.s2w.store.PrincipalStoreProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private PrincipalStoreProvider principalStoreProvider;

    @Override
    public BasicPrincipal register(BasicPrincipal regInfo) {
        BaseStore store = principalStoreProvider.resolve(regInfo);
        store.insert(regInfo);
        return regInfo;
    }
}
