package com.yuzi.denture.domain;

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

    public enum Company {
        SF("顺丰"), EMS("邮政特快"), YT("圆通"), ST("申通"), ZT("中通"), TT("天天"), YD("韵达");
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
}
