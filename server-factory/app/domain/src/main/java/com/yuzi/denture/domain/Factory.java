package com.yuzi.denture.domain;

public class Factory {
    
    //编号(id):
    private Long id;
    //名称(name):
    private String name;
    //联系方式(contact):
    private String contact;
    //地址(address):
    private String address;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
