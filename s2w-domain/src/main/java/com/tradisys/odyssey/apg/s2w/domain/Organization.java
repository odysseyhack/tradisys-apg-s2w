package com.tradisys.odyssey.apg.s2w.domain;

import java.util.List;

public class Organization extends BasicPrincipal {

    private static final long serialVersionUID = -7286961814441889028L;

    private String name;
    private String address;
    private String rsin; // similar to SSN
    private OrganizationStatus status;
    private List<Task> tasks;

    @Override
    public String getType() {
        return Role.ORGANIZATION.name();
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        Organization that = (Organization) o;

        if (!getName().equals(that.getName())) return false;
        if (!getAddress().equals(that.getAddress())) return false;
        if (!getRsin().equals(that.getRsin())) return false;
        if (getStatus() != that.getStatus()) return false;
        return getTasks() != null ? getTasks().equals(that.getTasks()) : that.getTasks() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getRsin().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + (getTasks() != null ? getTasks().hashCode() : 0);
        return result;
    }
}
