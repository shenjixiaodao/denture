package com.yuzi.denture.domain.aggregate;

import com.yuzi.denture.domain.FactoryUser;

import java.math.BigDecimal;

public class Salary {
    FactoryUser user;
    BigDecimal nominalSalary;
    BigDecimal actualSalary;
    BigDecimal commission;
    Long factoryId;
    String month;
    BigDecimal rewardPunishment;

    public FactoryUser getUser() {
        return user;
    }

    public void setUser(FactoryUser user) {
        this.user = user;
    }

    public BigDecimal getNominalSalary() {
        return nominalSalary;
    }

    public void setNominalSalary(BigDecimal nominalSalary) {
        this.nominalSalary = nominalSalary;
    }

    public BigDecimal getActualSalary() {
        return actualSalary;
    }

    public void setActualSalary(BigDecimal actualSalary) {
        this.actualSalary = actualSalary;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getRewardPunishment() {
        return rewardPunishment;
    }

    public void setRewardPunishment(BigDecimal rewardPunishment) {
        this.rewardPunishment = rewardPunishment;
    }
}
