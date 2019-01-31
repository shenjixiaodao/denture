package com.yuzi.denture.data.mapper;

import com.yuzi.denture.domain.ProductType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductTypeMapper {
    void save(ProductType type);
    void delete(Long id);
    List<ProductType> findTypesByFactoryId(Long factoryId);
}
