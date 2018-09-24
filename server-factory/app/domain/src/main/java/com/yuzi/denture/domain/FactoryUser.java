package com.yuzi.denture.domain;

import com.yuzi.denture.domain.util.RSAUtil;
import org.springframework.util.DigestUtils;

import java.security.PrivateKey;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FactoryUser {

    private static final String PrivateKeyStr = "";
    private final PrivateKey PriKey = RSAUtil.string2PrivateKey(PrivateKeyStr);

    //id:
     Long id;
    //factoryId
     Long factoryId;
    //name:
     String name;
    //contact:
     String contact;
     String password;
    //groupType:
     GroupType groupType;
    //joinDate:
     Date joinDate;
    List<FactoryRole> roles;

    public FactoryUser(Long factoryId, String name, String contact, GroupType groupType) {
        this.factoryId = factoryId;
        this.name = name;
        this.contact = contact;
        this.password = hashPWD(DefaultPWD(this.contact).getBytes());
        this.groupType = groupType;
    }
    public static void main(String[] strs) {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
     static String DefaultPWD(String contact) {
        return contact.substring(contact.length() - 6);
    }

     static String hashPWD(byte[] pwd) {
        return DigestUtils.md5DigestAsHex(pwd);
    }

    public boolean checkPWD(String encryptPWD) {
        byte[] pwd = RSAUtil.decrypt(encryptPWD.getBytes(), this.PriKey);
        String hashPwd = hashPWD(pwd);
        return Objects.equals(this.password, hashPwd);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<FactoryRole> getRoles() {
        return roles;
    }

    public void setRoles(List<FactoryRole> roles) {
        this.roles = roles;
    }

    public FactoryUser() {
    }
}