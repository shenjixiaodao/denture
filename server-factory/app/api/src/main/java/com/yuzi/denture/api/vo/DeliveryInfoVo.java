package com.yuzi.denture.api.vo;


import java.util.Date;

public class DeliveryInfoVo {
    //编号(id):
    private Long id;
    //产品编号(dentureId):
    private String dentureId;
    //物流公司(company):
    private String company;
    //快递单号(deliveryId):
    private String deliveryId;
    //发货时间(postDate):
    private Date postDate;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
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
