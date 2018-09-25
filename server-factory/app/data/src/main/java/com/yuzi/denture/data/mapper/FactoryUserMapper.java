package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.FactoryRole;
import com.yuzi.denture.domain.FactoryUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by liyou on 2018/9/21.
 */
@Mapper
public interface FactoryUserMapper {

    void save(FactoryUser user);
    void addRole(FactoryRole role);
    void update(FactoryUser user);
    FactoryUser findUserByContact(String contact);
    FactoryUser findUserById(Long id);
    List<FactoryUser> findUsersByFactoryId(Long factoryId);
}
