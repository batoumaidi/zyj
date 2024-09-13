package com.ityls.shoping_goods_service.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ityls.shoping_common.pojo.Specification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpecificationMapper extends BaseMapper<Specification> {
    Specification findById(Long id);
    // 根据商品类型查询商品规格
    List<Specification> findByProductTypeId(Long productTypeId);
}