package com.tradisys.odyssey.apg.s2w.keychain;

import com.wavesplatform.wavesj.PrivateKeyAccount;
import java.util.HashMap;
import java.util.Map;

public class S2WKeyChainDummyProvider implements KeychainProvider {

    private static final String APG_ID = "APG_ID";
    private Map<String, String> keychain;

    S2WKeyChainDummyProvider(){
        keychain = new HashMap<>();
        keychain.put(APG_ID, "awful clever luxury raise estate dose ring joy erase trend found expose awake fluid rail");
    }

    @Override
    public String generateSeed(String seedName) {
        String generateSeed = PrivateKeyAccount.generateSeed();
        keychain.put(seedName, generateSeed);
        return generateSeed;
    }

    @Override
    public String getSeedById(String id) {
        return keychain.get(id);
    }
}
