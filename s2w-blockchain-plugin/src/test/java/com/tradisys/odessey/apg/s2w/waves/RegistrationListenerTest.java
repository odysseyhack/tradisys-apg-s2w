package com.tradisys.odessey.apg.s2w.waves;

import com.tradisys.odyssey.apg.s2w.event.NewPrincipleEvent;
import com.tradisys.odyssey.apg.s2w.waves.RegistrationListener;
import org.junit.Test;

import java.math.BigDecimal;

public class RegistrationListenerTest {

    RegistrationListener listener = new RegistrationListener();

    @Test(expected = ClassCastException.class)
    public void testClassCastException() {
        listener.onApplicationEvent(new NewPrincipleEvent(new BigDecimal("100.00")));
    }
}
