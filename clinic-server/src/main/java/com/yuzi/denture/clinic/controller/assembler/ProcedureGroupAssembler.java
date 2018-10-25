package com.yuzi.denture.clinic.controller.assembler;

import com.yuzi.denture.clinic.controller.vo.ProcedureGroupVo;
import com.yuzi.denture.clinic.domain.ProcedureGroup;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class ProcedureGroupAssembler {
    public static ProcedureGroupVo toVo(ProcedureGroup group) {
        if(group == null)
            return null;
        ProcedureGroupVo vo = new ProcedureGroupVo();
        BeanUtils.copyProperties(group, vo);
        if(group.getType() != null)
            vo.setType(group.getType().name());
        return vo;
    }
    public static List<ProcedureGroupVo> toVos(List<ProcedureGroup> groups) {
        if(groups == null)
            return Collections.emptyList();
        List<ProcedureGroupVo> vos = new ArrayList<>(groups.size());
        for(ProcedureGroup group : groups) {
            ProcedureGroupVo vo = toVo(group);
            vos.add(vo);
        }
        return vos;
    }
}
