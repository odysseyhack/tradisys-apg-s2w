package com.tradisys.odyssey.apg.s2w.domain;

public class BasicPrincipal extends BasicIdentity {

    private static final long serialVersionUID = 984743260000720068L;

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
