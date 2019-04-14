package com.tradisys.odyssey.apg.s2w.domain;

import java.util.Objects;

public class Task extends BasicIdentity {

    private static final long serialVersionUID = -1774586340762927181L;

    private String name;
    private String description;
    private Double tokenCost;
    private Double votingPoints;
    private Organization organization;
    private TaskStatus status;
    private Boolean isCustomerFulfilled;
    private Boolean isOrganizationConfirmed;
    private Customer customer;

    public Task() {
    }

    public Task(String name, String description, Double tokenCost, Double votingPoints, Organization organization, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.tokenCost = tokenCost;
        this.votingPoints = votingPoints;
        this.organization = organization;
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Double getTokenCost() {
        return tokenCost;
    }

    public Double getVotingPoints() {
        return votingPoints;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getName(), task.getName()) && Objects.equals(getDescription(), task.getDescription()) && Objects.equals(getTokenCost(), task.getTokenCost()) && Objects.equals(getVotingPoints(), task.getVotingPoints()) && Objects.equals(getOrganization(), task.getOrganization()) && getStatus() == task.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getTokenCost(), getVotingPoints(), getOrganization(), getStatus());
    }
}
