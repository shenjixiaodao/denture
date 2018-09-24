package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.FactoryUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by liyou on 2018/9/21.
 */
@Mapper
public interface FactoryUserMapper {

    void save(FactoryUser user);
    FactoryUser findUserByContact(String contact);
    List<FactoryUser> findUsersByFactoryId(Long factoryId);
}
