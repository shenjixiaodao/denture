package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.SupplierVo;
import com.yuzi.denture.domain.Supplier;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/10/21.
 */
public class SupplierAssembler {

    public static List<SupplierVo> toVos(List<Supplier> suppliers) {
        if(suppliers == null)
            return Collections.emptyList();
        List<SupplierVo> vos = new ArrayList<>(suppliers.size());
        for(Supplier supplier:suppliers) {
            SupplierVo vo = toVo(supplier);
            vos.add(vo);
        }
        return vos;
    }

    public static SupplierVo toVo(Supplier supplier) {
        if(supplier==null)
            return null;
        SupplierVo vo = new SupplierVo();
        BeanUtils.copyProperties(supplier, vo);
        return vo;
    }
}
