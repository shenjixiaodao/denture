package com.yuzi.denture.domain.aggregate;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liyou on 2018/11/18.
 */
public class TotalIngredientStatistic {
    Long factoryId;
    BigDecimal year;
    BigDecimal month;
    BigDecimal day;
    Date date;

    public BigDecimal getDay() {
        return day;
    }

    public void setDay(BigDecimal day) {
        this.day = day;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public BigDecimal getYear() {
        return year;
    }

    public void setYear(BigDecimal year) {
        this.year = year;
    }

    public BigDecimal getMonth() {
        return month;
    }

    public void setMonth(BigDecimal month) {
        this.month = month;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
