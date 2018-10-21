package com.yuzi.denture.domain;

/**
 * Created by liyou on 2018/10/21.
 */
public class Supplier {
    Long id;
    Long factoryId;
    String name;
    String address;
    String contact;

    public Supplier(Long factoryId, String name, String address, String contact) {
        this.factoryId = factoryId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Supplier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
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
