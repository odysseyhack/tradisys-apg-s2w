package com.tradisys.odyssey.apg.s2w.event;

import org.springframework.context.ApplicationEvent;

public class NewPrincipleEvent extends ApplicationEvent {

    private static final long serialVersionUID = -1423900983731327317L;

    public NewPrincipleEvent(Object source) {
        super(source);
    }
}
