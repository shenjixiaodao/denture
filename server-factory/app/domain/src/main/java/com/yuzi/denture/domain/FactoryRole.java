package com.yuzi.denture.domain;

/**
 * Created by liyou on 2018/9/24.
 */
public class FactoryRole {
    Long id;
    Long uid;
    String role;

    public FactoryRole(Long uid, String role) {
        this.uid = uid;
        this.role = role;
    }

    public FactoryRole(String role) {
        this.role = role;
    }

    public FactoryRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
