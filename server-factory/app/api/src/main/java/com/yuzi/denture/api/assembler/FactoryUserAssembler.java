package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.FactoryUserVo;
import com.yuzi.denture.domain.FactoryUser;
import org.springframework.beans.BeanUtils;

/**
 * Created by liyou on 2018/9/19.
 */
public class FactoryUserAssembler {

    public static FactoryUserVo toVo(FactoryUser user) {
        if(user == null)
            return null;
        FactoryUserVo vo = new FactoryUserVo();
        BeanUtils.copyProperties(user, vo);
        vo.setGroupType(user.getGroupType().name());
        return vo;
    }
}
