package com.yuzi.denture.domain.hj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Customer implements Serializable {
    Long id;
    String name;
    String property;
    String bank_account;
    String city;
    String address;
    String delivery;
    String equipment;
    String product;
    String production;
    String work_status;
    String purchase_manager;
    String purchase_contact;
    String is_trusted_manager;
    String purchase_style;
    String is_long_term;
    String is_visit_company;
    BigDecimal total_unpaid_amount;
    Integer is_customer;
    List<Order> orders;
    List<Visit> visits;

    public Customer(String name) {
        this.name = name;
    }

    public Customer() {
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public Integer getIs_customer() {
        return is_customer;
    }

    public void setIs_customer(Integer is_customer) {
        this.is_customer = is_customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWork_status() {
        return work_status;
    }

    public void setWork_status(String work_status) {
        this.work_status = work_status;
    }

    public String getPurchase_manager() {
        return purchase_manager;
    }

    public void setPurchase_manager(String purchase_manager) {
        this.purchase_manager = purchase_manager;
    }

    public String getPurchase_contact() {
        return purchase_contact;
    }

    public void setPurchase_contact(String purchase_contact) {
        this.purchase_contact = purchase_contact;
    }

    public String getIs_trusted_manager() {
        return is_trusted_manager;
    }

    public void setIs_trusted_manager(String is_trusted_manager) {
        this.is_trusted_manager = is_trusted_manager;
    }

    public String getPurchase_style() {
        return purchase_style;
    }

    public void setPurchase_style(String purchase_style) {
        this.purchase_style = purchase_style;
    }

    public String getIs_long_term() {
        return is_long_term;
    }

    public void setIs_long_term(String is_long_term) {
        this.is_long_term = is_long_term;
    }

    public String getIs_visit_company() {
        return is_visit_company;
    }

    public void setIs_visit_company(String is_visit_company) {
        this.is_visit_company = is_visit_company;
    }

    public BigDecimal getTotal_unpaid_amount() {
        return total_unpaid_amount;
    }

    public void setTotal_unpaid_amount(BigDecimal total_unpaid_amount) {
        this.total_unpaid_amount = total_unpaid_amount;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
