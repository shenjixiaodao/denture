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
}
