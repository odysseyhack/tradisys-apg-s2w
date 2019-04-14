package com.tradisys.odyssey.apg.s2w.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Customer extends BasicPrincipal {

    private static final long serialVersionUID = 1397494878295063429L;

    private String firstName;
    private String secondName;
    private String bsn;
    private String address;
    private Date birth;
    private List<Task> tasks;

    public Customer() {
    }

    @Override
    public String getType() {
        return Role.CUSTOMER.name();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (!getFirstName().equals(customer.getFirstName())) return false;
        if (!getSecondName().equals(customer.getSecondName())) return false;
        if (!getBsn().equals(customer.getBsn())) return false;
        if (!getAddress().equals(customer.getAddress())) return false;
        return getBirth().equals(customer.getBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }
}
