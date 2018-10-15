package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.ProcedureVo;
import com.yuzi.denture.domain.Procedure;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class ProcedureAssembler {
    public static ProcedureVo toVo(Procedure procedure) {
        if(procedure == null)
            return null;
        ProcedureVo vo = new ProcedureVo();
        BeanUtils.copyProperties(procedure, vo);
        vo.setOperator(FactoryUserAssembler.toVo(procedure.getOperator()));
        return vo;
    }

    public static List<ProcedureVo> toVos(List<Procedure> procedures) {
        if(procedures == null)
            return Collections.emptyList();
        List<ProcedureVo> vos = new ArrayList<>(procedures.size());
        for(Procedure procedure: procedures) {
            ProcedureVo vo = toVo(procedure);
            vos.add(vo);
        }
        return vos;
    }
}
