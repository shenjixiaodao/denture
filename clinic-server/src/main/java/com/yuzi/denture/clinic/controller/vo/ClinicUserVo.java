package com.yuzi.denture.clinic.controller.vo;


public class ClinicUserVo {
    //编号(id):
    private Long id;
    //诊所编号(clinicId):
    private ClinicVo clinic;
    //角色(clinicRole):
    private String role;
    //电话(contact):
    private String contact;
    //姓名(name):
    private String name;
    String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClinicVo getClinic() {
        return clinic;
    }

    public void setClinic(ClinicVo clinic) {
        this.clinic = clinic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
