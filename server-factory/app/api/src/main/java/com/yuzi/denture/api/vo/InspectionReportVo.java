package com.yuzi.denture.api.vo;

import java.util.List;

public class InspectionReportVo {
    String dentureId;
    String sampleDate;
    String startDate;
    String endDate;
    String conclusion;
    String comment;
    List<InspectionItemVo> items;

    public String getDentureId() {
        return dentureId;
    }

    public void setDentureId(String dentureId) {
        this.dentureId = dentureId;
    }

    public String getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(String sampleDate) {
        this.sampleDate = sampleDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<InspectionItemVo> getItems() {
        return items;
    }

    public void setItems(List<InspectionItemVo> items) {
        this.items = items;
    }
}
