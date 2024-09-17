package com.ityls.shoping_category_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ityls.shoping_common.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}