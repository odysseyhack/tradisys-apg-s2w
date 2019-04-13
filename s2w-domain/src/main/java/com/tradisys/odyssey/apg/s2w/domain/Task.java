package com.tradisys.odyssey.apg.s2w.domain;

import java.util.List;

public class Task extends BasicIdentity {

    private static final long serialVersionUID = -1774586340762927181L;

    private String name;
    private String description;
    private Double tokenCost;
    private Double votingPoints;
    private TaskStatus status;
    private List<Customer> assignedUsers;

    public List<Customer> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<Customer> assignedUsers) {
        this.assignedUsers = assignedUsers;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTokenCost() {
        return tokenCost;
    }

    public void setTokenCost(Double tokenCost) {
        this.tokenCost = tokenCost;
    }

    public Double getVotingPoints() {
        return votingPoints;
    }

    public void setVotingPoints(Double votingPoints) {
        this.votingPoints = votingPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (!getName().equals(task.getName())) return false;
        if (!getDescription().equals(task.getDescription())) return false;
        if (!getTokenCost().equals(task.getTokenCost())) return false;
        if (!getVotingPoints().equals(task.getVotingPoints())) return false;
        if (getStatus() != task.getStatus()) return false;
        return getAssignedUsers() != null ? getAssignedUsers().equals(task.getAssignedUsers()) : task.getAssignedUsers() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getTokenCost().hashCode();
        result = 31 * result + getVotingPoints().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + (getAssignedUsers() != null ? getAssignedUsers().hashCode() : 0);
        return result;
    }
}
