package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.InspectionItemVo;
import com.yuzi.denture.api.vo.InspectionReportVo;
import com.yuzi.denture.domain.InspectionItem;
import com.yuzi.denture.domain.InspectionReport;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class InspectionAssembler {

    public static InspectionReportVo to(InspectionReport inspection) {
        if(inspection == null)
            return null;
        InspectionReportVo vo = new InspectionReportVo();
        BeanUtils.copyProperties(inspection, vo);
        vo.setItems(toVos(inspection.getItems()));
        return vo;
    }

    public static InspectionItemVo to(InspectionItem item) {
        if(item==null)
            return null;
        InspectionItemVo vo = new InspectionItemVo();
        BeanUtils.copyProperties(item, vo);
        return vo;
    }
    public static List<InspectionItemVo> toVos(List<InspectionItem> items) {
        if(items==null)
            return null;
        List<InspectionItemVo> vos = new ArrayList<>(items.size());
        for(InspectionItem item:items) {
            vos.add(to(item));
        }
        return vos;
    }
}
