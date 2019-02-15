package com.yuzi.denture.domain.hj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Customer implements Serializable {
    Long id;
    String name;
    String telephone;
    String contact;
    String contact_position;
    String contact_phone;
    String contact_is_shareholder;
    String contact_other;
    String region;
    String address;
    String source;
    String type;
    String state_owned;
    String scale;
    String furnace_number;
    String start_date;
    String customer_manager;
    String gmt_created;
    String gmt_modified;
    String product_type;
    String comment;
    BigDecimal total_unpaid_amount;
    List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact_position() {
        return contact_position;
    }

    public void setContact_position(String contact_position) {
        this.contact_position = contact_position;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_is_shareholder() {
        return contact_is_shareholder;
    }

    public void setContact_is_shareholder(String contact_is_shareholder) {
        this.contact_is_shareholder = contact_is_shareholder;
    }

    public String getContact_other() {
        return contact_other;
    }

    public void setContact_other(String contact_other) {
        this.contact_other = contact_other;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState_owned() {
        return state_owned;
    }

    public void setState_owned(String state_owned) {
        this.state_owned = state_owned;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getFurnace_number() {
        return furnace_number;
    }

    public void setFurnace_number(String furnace_number) {
        this.furnace_number = furnace_number;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getCustomer_manager() {
        return customer_manager;
    }

    public void setCustomer_manager(String customer_manager) {
        this.customer_manager = customer_manager;
    }

    public String getGmt_created() {
        return gmt_created;
    }

    public void setGmt_created(String gmt_created) {
        this.gmt_created = gmt_created;
    }

    public String getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(String gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getTotal_unpaid_amount() {
        return total_unpaid_amount;
    }

    public void setTotal_unpaid_amount(BigDecimal total_unpaid_amount) {
        this.total_unpaid_amount = total_unpaid_amount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", contact='" + contact + '\'' +
                ", contact_position='" + contact_position + '\'' +
                ", contact_phone='" + contact_phone + '\'' +
                ", contact_is_shareholder='" + contact_is_shareholder + '\'' +
                ", contact_other='" + contact_other + '\'' +
                ", address='" + address + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", state_owned='" + state_owned + '\'' +
                ", scale='" + scale + '\'' +
                ", furnace_number='" + furnace_number + '\'' +
                ", start_date='" + start_date + '\'' +
                ", customer_manager='" + customer_manager + '\'' +
                ", gmt_created='" + gmt_created + '\'' +
                ", gmt_modified='" + gmt_modified + '\'' +
                ", product_type='" + product_type + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
