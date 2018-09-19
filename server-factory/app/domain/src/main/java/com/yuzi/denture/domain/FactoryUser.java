package com.yuzi.denture.domain;

import java.util.Date;

public class FactoryUser {

    //id:
    private Long id;
    //name:
    private String name;
    //contact:
    private String contact;
    //groupType:
    private GroupType groupType;
    //joinDate:
    private Date joinDate;


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

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public FactoryUser(Long id) {
        this.id = id;
    }

    public FactoryUser() {
    }
}
