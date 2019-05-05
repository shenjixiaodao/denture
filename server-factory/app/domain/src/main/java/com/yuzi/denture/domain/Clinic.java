package com.yuzi.denture.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Clinic {
    //id:
    private Long id;
    //名称(name):
    private String name;
    String region;
    //地址(address):
    private String address;
    //联系方式(contact):
    private String contact;
    @JsonIgnore
    List<ClinicUser> users;

    public Clinic(String name, String region, String address, String contact) {
        this.name = name;
        this.region = region;
        this.address = address;
        this.contact = contact;
    }

    public Clinic(Long id) {
        this.id = id;
    }

    public Clinic() {
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
