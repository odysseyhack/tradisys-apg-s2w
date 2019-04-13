package com.tradisys.odyssey.apg.s2w.domain;

import java.io.Serializable;

public abstract class BasicIdentity implements Serializable {
    private static final long serialVersionUID = 6275090844224492547L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
