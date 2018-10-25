package com.yuzi.denture.clinic.domain;

import java.util.Date;

public class DeliveryInfo {
    
    //编号(id):
    private Long id;
    //产品编号(dentureId):
    private String dentureId;
    //物流公司(company):
    private Company company;
    //快递单号(deliveryId):
    private String deliveryId;
    //发货时间(postDate):
    private Date postDate;

    /**
     * 新建物流信息
     * @param dentureId
     * @param company
     * @param deliveryId
     */
    public DeliveryInfo(String dentureId, Company company, String deliveryId) {
        this.dentureId = dentureId;
        this.company = company;
        this.deliveryId = deliveryId;
        if(company== Company.AskFor) {
            //到店取的订单没有订单号，使用当前毫秒数作为单号
            this.deliveryId = new Date().getTime() + "";
        }
        this.postDate = new Date();
    }

    public DeliveryInfo() {
    }

    public enum Company {
        SF("顺丰"), EMS("邮政特快"), YT("圆通"), ST("申通"), ZT("中通"), TT("天天"), YD("韵达"), AskFor("到店取");
        private String text;
        Company(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }
        public static Company typeOf(String type) {
            for(Company result : Company.values()){
                if(result.name().toLowerCase().equals(type.toLowerCase()))
                    return result;
            }
            throw new IllegalArgumentException("未知公司类型");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDentureId() {
        return dentureId;
    }

    public void setDentureId(String dentureId) {
        this.dentureId = dentureId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
