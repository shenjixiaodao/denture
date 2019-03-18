package com.yuzi.denture.domain.hj;

public class Visit {

    Long id;
    Long customer_id;
    String visit_date;
    String interview;
    String expansion_plan;
    String rebuild_plan;
    String purchase_intended;
    String feedback;
    String atitude;

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    public String getExpansion_plan() {
        return expansion_plan;
    }

    public void setExpansion_plan(String expansion_plan) {
        this.expansion_plan = expansion_plan;
    }

    public String getRebuild_plan() {
        return rebuild_plan;
    }

    public void setRebuild_plan(String rebuild_plan) {
        this.rebuild_plan = rebuild_plan;
    }

    public String getPurchase_intended() {
        return purchase_intended;
    }

    public void setPurchase_intended(String purchase_intended) {
        this.purchase_intended = purchase_intended;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getAtitude() {
        return atitude;
    }

    public void setAtitude(String atitude) {
        this.atitude = atitude;
    }
}
