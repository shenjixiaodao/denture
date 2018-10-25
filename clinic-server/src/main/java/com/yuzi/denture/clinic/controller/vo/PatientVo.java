package com.yuzi.denture.clinic.controller.vo;

public class PatientVo {

    //编号(id):
    private Long id;
    //诊所编号(clinicId):
    private Long clinicId;
    //姓名(name):
    private String name;
    //联系方式(contact):
    private String contact;

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
}
