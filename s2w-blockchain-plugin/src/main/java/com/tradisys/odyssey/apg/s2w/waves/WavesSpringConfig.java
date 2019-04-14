package com.tradisys.odyssey.apg.s2w.waves;

import com.wavesplatform.wavesj.Node;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WavesSpringConfig {

    @Bean
    public Node wavesNode() throws Exception {
        return new Node(ConfigProperties.URL, ConfigProperties.CHAIN);
    }

    @Bean
    public RegistrationListener registrationListener() {
        return new RegistrationListener();
    }
}
