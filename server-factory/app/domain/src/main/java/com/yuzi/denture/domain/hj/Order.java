package com.yuzi.denture.domain.hj;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    Long id;
    Long customer_id;
    String customer_name;
    String created_date;
    String deadline;
    String salesman;
    String paid_type;
    BigDecimal paid_amount;
    String paid_date;
    BigDecimal unpaid_amount;
    BigDecimal prepaid_amount;
    String prepaid_date;
    Double recycled_cu;
    String comment;
    String delivery_date;
    List<OrderDetail> details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getPaid_type() {
        return paid_type;
    }

    public void setPaid_type(String paid_type) {
        this.paid_type = paid_type;
    }

    public BigDecimal getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(BigDecimal paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getPaid_date() {
        return paid_date;
    }

    public void setPaid_date(String paid_date) {
        this.paid_date = paid_date;
    }

    public BigDecimal getUnpaid_amount() {
        return unpaid_amount;
    }

    public void setUnpaid_amount(BigDecimal unpaid_amount) {
        this.unpaid_amount = unpaid_amount;
    }

    public BigDecimal getPrepaid_amount() {
        return prepaid_amount;
    }

    public void setPrepaid_amount(BigDecimal prepaid_amount) {
        this.prepaid_amount = prepaid_amount;
    }

    public String getPrepaid_date() {
        return prepaid_date;
    }

    public void setPrepaid_date(String prepaid_date) {
        this.prepaid_date = prepaid_date;
    }

    public Double getRecycled_cu() {
        return recycled_cu;
    }

    public void setRecycled_cu(Double recycled_cu) {
        this.recycled_cu = recycled_cu;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
}
