package com.tradisys.odyssey.apg.s2w.domain;

public class Organization extends BasicPrincipal {

    private static final long serialVersionUID = -7286961814441889028L;

    private String name;
    private String address;
    private String rsin; // similar to SSN

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRsin() {
        return rsin;
    }

    public void setRsin(String rsin) {
        this.rsin = rsin;
    }
}
