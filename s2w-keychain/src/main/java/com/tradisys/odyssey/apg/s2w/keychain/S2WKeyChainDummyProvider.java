package com.tradisys.odyssey.apg.s2w.keychain;

import com.wavesplatform.wavesj.PrivateKeyAccount;
import java.util.HashMap;
import java.util.Map;

public class S2WKeyChainDummyProvider implements KeychainProvider {

    private Map<String, String> keychain;

    S2WKeyChainDummyProvider(){
        keychain = new HashMap<>();
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

    public Map<String, String> getKeychain() {
        return keychain;
    }

    public void setKeychain(Map<String, String> keychain) {
        this.keychain = keychain;
    }
}
