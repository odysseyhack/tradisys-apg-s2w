package com.tradisys.odyssey.apg.s2w.services.dto;

import com.tradisys.odyssey.apg.s2w.domain.Role;

import java.io.Serializable;

public abstract class BaseRegDto implements Serializable {
    private static final long serialVersionUID = 5176632444929225049L;

    private Long id;
    private Role type;

    public Role getType() {
        return type;
    }

    public void setType(Role type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}