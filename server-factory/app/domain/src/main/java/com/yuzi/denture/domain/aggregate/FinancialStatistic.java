package com.yuzi.denture.domain.aggregate;

import java.math.BigDecimal;

public class FinancialStatistic {
    //支出
    BigDecimal ingredientPay;
    BigDecimal salaryPay;
    BigDecimal socialSecurityPay;
    BigDecimal welfarePay;
    BigDecimal commissionPay;
    //收入
    BigDecimal income;

    public BigDecimal getSocialSecurityPay() {
        return socialSecurityPay;
    }

    public void setSocialSecurityPay(BigDecimal socialSecurityPay) {
        this.socialSecurityPay = socialSecurityPay;
    }

    public BigDecimal getWelfarePay() {
        return welfarePay;
    }

    public void setWelfarePay(BigDecimal welfarePay) {
        this.welfarePay = welfarePay;
    }

    public BigDecimal getIngredientPay() {
        return ingredientPay;
    }

    public void setIngredientPay(BigDecimal ingredientPay) {
        this.ingredientPay = ingredientPay;
    }

    public BigDecimal getSalaryPay() {
        return salaryPay;
    }

    public void setSalaryPay(BigDecimal salaryPay) {
        this.salaryPay = salaryPay;
    }

    public BigDecimal getCommissionPay() {
        return commissionPay;
    }

    public void setCommissionPay(BigDecimal commissionPay) {
        this.commissionPay = commissionPay;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
