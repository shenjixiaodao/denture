package com.yuzi.denture.api.assembler;

import com.yuzi.denture.api.vo.FactoryUserVo;
import com.yuzi.denture.domain.FactoryRole;
import com.yuzi.denture.domain.FactoryUser;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liyou on 2018/9/19.
 */
public class FactoryUserAssembler {

    public static List<FactoryUserVo> toVos(List<FactoryUser> users) {
        if(users == null)
            return Collections.emptyList();
        List<FactoryUserVo> vos = new ArrayList<>(users.size());
        for(FactoryUser user : users) {
            FactoryUserVo vo = toVo(user);
            vos.add(vo);
        }
        return vos;
    }

    public static FactoryUserVo toVo(FactoryUser user) {
        if(user == null)
            return null;
        FactoryUserVo vo = new FactoryUserVo();
        BeanUtils.copyProperties(user, vo);
        if(user.getGroupType() != null)
            vo.setGroupType(user.getGroupType().name());
        if(user.getRoles() != null) {
            List<String> rs = new ArrayList<>(user.getRoles().size());
            List<FactoryRole> roles = user.getRoles();
            for(FactoryRole role : roles)
                rs.add(role.getRole().name());
            vo.setRoles(rs);
        }
        return vo;
    }
}
