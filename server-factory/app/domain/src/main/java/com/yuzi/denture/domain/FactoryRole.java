package com.yuzi.denture.domain;

/**
 * Created by liyou on 2018/9/24.
 */
public class FactoryRole {
    Long id;
    Long uid;
    Role role;

    public FactoryRole(Role role) {
        this.role = role;
    }

    public FactoryRole() {
    }

    public enum Role {
        ShiGao("石膏技师"),
        ShiGaoLeader("石膏组长"),
        LaXing("蜡型技师"),
        LaXingLeader("蜡型组长"),
        CheJin("车金技师"),
        ChejinLeader("车金组长"),
        ChongJiao("充胶技师"),
        ChongjiaoLeader("充胶组长"),
        ShangCi("上瓷技师"),
        ShangCiLeader("上瓷组长"),
        CheCi("车瓷"),
        CheCiLeader("车瓷组长"),
        Comprehensive("综合管理人员"),
        ComprehensiveLeader("综合部主管"),
        Market("市场人员"),
        MarketLeader("市场主管"),
        Management("管理层");

        private String text;
        Role(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }
        public GroupType group() {
            for(GroupType type : GroupType.values()) {
                if(this.name().toLowerCase()
                        .contains(type.name().toLowerCase())) {
                    return type;
                }
            }
            return GroupType.Other;
        }
        public static Role typeOf(String type) {
            for(Role result : Role.values()){
                if(result.name().toLowerCase().equals(type.toLowerCase()))
                    return result;
            }
            throw new IllegalArgumentException("未知角色类型");
        }
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
