package com.yuzi.denture.domain;

public class ClinicUser {

    //编号(id):
    private Long id;
    //诊所编号(clinicId):
    private Long clinicId;
    //角色(clinicRole):
    private ClinicRole role;
    //电话(contact):
    private String contact;
    //姓名(name):
    private String name;

    public ClinicUser(Long id) {
        this.id = id;
    }

    public ClinicUser(String contact, String name) {
        this.role = ClinicRole.Dentist;
        this.contact = contact;
        this.name = name;
    }

    public ClinicUser() {
    }

    public enum  ClinicRole {
        Dentist("医生"), Nurse("护士"), Other("其它");
        private String text;
        ClinicRole(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }
    }

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

    public ClinicRole getRole() {
        return role;
    }

    public void setRole(ClinicRole role) {
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
