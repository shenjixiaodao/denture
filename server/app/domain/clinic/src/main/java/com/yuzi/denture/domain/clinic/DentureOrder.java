package com.yuzi.denture.domain.clinic;

public class DentureOrder {
    private String id;
    private String dentureId;
    private Long clinicId;
    private Long factoryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDentureId() {
        return dentureId;
    }

    public void setDentureId(String dentureId) {
        this.dentureId = dentureId;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public DentureOrder(Long clinicId, Long factoryId) {
        this.clinicId = clinicId;
        this.factoryId = factoryId;
    }

    public DentureOrder() {
    }
}
