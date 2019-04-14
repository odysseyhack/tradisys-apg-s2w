package com.tradisys.odyssey.apg.s2w.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Customer.class, name = "CUSTOMER"),
        @JsonSubTypes.Type(value = Organization.class, name = "ORGANIZATION")
})
public abstract class BasicPrincipal extends BasicIdentity {

    private static final long serialVersionUID = 984743260000720068L;

    private Role role;

    public abstract String getType();

    @Override
    public Long getId() {
        return super.getId();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
