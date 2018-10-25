package com.yuzi.denture.clinic.controller.vo;


public class ClinicUserVo {
    //编号(id):
    private Long id;
    //诊所编号(clinicId):
    private Long clinicId;
    //角色(clinicRole):
    private String role;
    //电话(contact):
    private String contact;
    //姓名(name):
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
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
}
