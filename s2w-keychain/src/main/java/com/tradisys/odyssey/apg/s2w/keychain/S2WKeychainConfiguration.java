package com.tradisys.odyssey.apg.s2w.keychain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S2WKeychainConfiguration {

    @Bean
    public KeychainProvider s2WKeyChainDummyProvider() {
        return new S2WKeyChainDummyProvider();
    }

}
