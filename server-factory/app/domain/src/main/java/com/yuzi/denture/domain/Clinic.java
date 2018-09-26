package com.yuzi.denture.domain;

public class Clinic {
    //id:
    private Long id;
    //名称(name):
    private String name;
    //地址(address):
    private String address;
    //联系方式(contact):
    private String contact;

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
}
