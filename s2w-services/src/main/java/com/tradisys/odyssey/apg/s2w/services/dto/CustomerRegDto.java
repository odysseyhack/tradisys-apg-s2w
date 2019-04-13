package com.tradisys.odyssey.apg.s2w.services.dto;

import java.util.Date;

public class CustomerRegDto extends BaseRegDto {

    private static final long serialVersionUID = -868111155283358169L;

    private String firstName;
    private String secondName;
    private String bsn;
    private String address;
    private Date birth;

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
}
