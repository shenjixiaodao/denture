package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.Denture;
import com.yuzi.denture.domain.InspectionItem;
import com.yuzi.denture.domain.InspectionReport;
import com.yuzi.denture.domain.criteria.DentureCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DentureMapper {
    void save(Denture denture);
    void update(Denture denture);
    Denture findByDeliveryInfo(Map paras);
    Denture findByDentureId(String dentureId);
    List<Denture> findDenturesByFactoryId(Long factoryId);

    List<Denture> findWaitingDentures(DentureCriteria criteria);
    List<Denture> findDoingDentures(DentureCriteria criteria);
    List<Denture> findDoneDentures(DentureCriteria criteria);

    Integer countDentures(DentureCriteria criteria);
    List<Denture> findDenturesByCriteria(DentureCriteria criteria);
    List<Denture> findDentures(DentureCriteria criteria);

    void saveInspectionReport(InspectionReport report);
    void saveInspectionItem(InspectionItem item);
    InspectionReport findInspectionByDentureId(String dentureId);
}
