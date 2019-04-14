package com.tradisys.odyssey.apg.s2w.waves;

import com.tradisys.odyssey.apg.s2w.domain.BasicPrincipal;
import com.tradisys.odyssey.apg.s2w.domain.Role;
import com.tradisys.odyssey.apg.s2w.event.NewPrincipleEvent;
import com.tradisys.odyssey.apg.s2w.keychain.KeychainProvider;
import com.wavesplatform.wavesj.Node;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.io.IOException;

public class RegistrationListener implements ApplicationListener<NewPrincipleEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationListener.class);

    @Autowired
    private KeychainProvider keychainProvider;

    @Autowired
    private Node node;

    @Override
    public void onApplicationEvent(NewPrincipleEvent newPrincipleEvent) {
        try {
            BasicPrincipal principal = (BasicPrincipal) newPrincipleEvent.getSource();
            if (principal.getRole().equals(Role.ORGANIZATION)) {
                String orgSeed = keychainProvider.getSeedById(principal.getId().toString());
                String apgSeed = keychainProvider.getSeedById(KeychainProvider.APG_ID);

                PrivateKeyAccount orgPK = toPrivateKey(orgSeed);
                PrivateKeyAccount apgPK = toPrivateKey(apgSeed);

                LOGGER.info("Organization registration in Waves blockchain: {}", principal.toString());
                node.transfer(apgPK, ConfigProperties.S2WToken, orgPK.getAddress(), ConfigProperties.ORG_INIT_BALANCE, ConfigProperties.TRANSFER_FEE, null, "");
            }
        } catch (IOException ex) {
            LOGGER.error("Could not transfer funds", ex);
            throw new RuntimeException("Could not init organization account", ex);
        }
    }

    private PrivateKeyAccount toPrivateKey(String seed) {
        return PrivateKeyAccount.fromSeed(seed, 0, ConfigProperties.CHAIN);
    }
}
