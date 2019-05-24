package com.yuzi.denture.data.mapper;


import com.yuzi.denture.domain.Function;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Created by liyou on 2018/12/19.
 */
@Mapper
public interface FunctionMapper {
    List<Function> findFunctions();
}
