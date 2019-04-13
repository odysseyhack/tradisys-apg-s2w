package com.tradisys.odyssey.apg.s2w.domain;

import java.util.Objects;

public class Organization extends BasicPrincipal {

    private static final long serialVersionUID = -7286961814441889028L;

    private String name;
    private String address;
    private String rsin;
    private OrganizationStatus status;

    public Organization() {
    }

    public Organization(String name, String address, String rsin, OrganizationStatus status) {
        this.name = name;
        this.address = address;
        this.rsin = rsin;
        this.status = status;
    }

    public OrganizationStatus getStatus() {
        return status;
    }

    public void setStatus(OrganizationStatus status) {
        this.status = status;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getRsin(), that.getRsin()) &&
                getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getAddress(), getRsin(), getStatus());
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rsin='" + rsin + '\'' +
                ", status=" + status +
                '}';
    }
}
