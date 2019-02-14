package com.yuzi.denture.domain.hj;

import java.math.BigDecimal;

public class OrderDetail {
    Long id;
    Long order_id;
    String product_name;
    String specification;
    String unit;
    Double number;
    BigDecimal unit_price;
    BigDecimal processing_unit_price;
    BigDecimal ingredient_amount;
    String supplier;
    String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public BigDecimal getProcessing_unit_price() {
        return processing_unit_price;
    }

    public void setProcessing_unit_price(BigDecimal processing_unit_price) {
        this.processing_unit_price = processing_unit_price;
    }

    public BigDecimal getIngredient_amount() {
        return ingredient_amount;
    }

    public void setIngredient_amount(BigDecimal ingredient_amount) {
        this.ingredient_amount = ingredient_amount;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
