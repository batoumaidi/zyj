package com.ityls.shoping_category_service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ityls.shoping_category_service.mapper.CategoryMapper;
import com.ityls.shoping_common.pojo.Category;
import com.ityls.shoping_common.service.CategoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@DubboService
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateById(category);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Category category = categoryMapper.selectById(id);
        category.setStatus(status);
        categoryMapper.updateById(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public void delete(Long[] ids) {
        categoryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public Page<Category> search(int page, int size) {
        return categoryMapper.selectPage(new Page(page,size),null);
    }

    @Override
    public List<Category> findAll() {
        // 从数据库查询所有启用的广告
        QueryWrapper<Category> queryWrapper = new QueryWrapper();
        queryWrapper.eq("status",1);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return categories;
    }
}