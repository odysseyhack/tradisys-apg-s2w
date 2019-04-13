package com.tradisys.odyssey.apg.s2w.domain;

import java.util.Date;
import java.util.Objects;

public class Customer extends BasicPrincipal {

    private static final long serialVersionUID = 1397494878295063429L;

    private String firstName;
    private String secondName;
    private String bsn;
    private String address;
    private Date birth;

    public Customer() {
    }

    public Customer(String firstName, String secondName, String bsn, String address, Date birth) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.bsn = bsn;
        this.address = address;
        this.birth = birth;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getSecondName(), customer.getSecondName()) &&
                Objects.equals(getBsn(), customer.getBsn()) &&
                Objects.equals(getAddress(), customer.getAddress()) &&
                Objects.equals(getBirth(), customer.getBirth());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFirstName(), getSecondName(), getBsn(), getAddress(), getBirth());
    }
}
