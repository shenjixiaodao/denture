package com.yuzi.denture.domain.criteria;


/**
 * Created by liyou on 2018/12/19.
 */
public class AggregateOrderCriteria {

    Long factoryId;
    String startTime;
    String endTime;

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
