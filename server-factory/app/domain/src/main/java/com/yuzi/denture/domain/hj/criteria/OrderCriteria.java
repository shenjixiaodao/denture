package com.yuzi.denture.domain.hj.criteria;

public class OrderCriteria extends PageCriteria {
    String customer_name;
    String order_month;

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getOrder_month() {
        return order_month;
    }

    public void setOrder_month(String order_month) {
        this.order_month = order_month;
    }
}
