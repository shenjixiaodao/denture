package com.yuzi.denture.clinic.domain;

import java.util.List;

public class Clinic {
    //id:
    private Long id;
    //名称(name):
    private String name;
    //地址(address):
    private String address;
    //联系方式(contact):
    private String contact;
    List<ClinicUser> users;

    public Clinic(Long id) {
        this.id = id;
    }

    public Clinic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<ClinicUser> getUsers() {
        return users;
    }

    public void setUsers(List<ClinicUser> users) {
        this.users = users;
    }
}
