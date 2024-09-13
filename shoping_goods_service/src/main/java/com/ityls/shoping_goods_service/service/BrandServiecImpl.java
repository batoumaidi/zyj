package com.ityls.shoping_goods_service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ityls.shoping_common.pojo.Brand;
import com.ityls.shoping_common.service.BrandService;
import com.ityls.shoping_goods_service.mapper.BrandMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@DubboService
@Transactional
public class BrandServiecImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void delete(Long id) {
        brandMapper.deleteById(id);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateById(brand);
    }

    @Override
    public Brand findById(Long id) {
      return brandMapper.selectById(id);
    }

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectList(null);
    }

    @Override
    public Page<Brand> search(Brand brand, int page, int size) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (brand != null && brand.getName() != null) {
            queryWrapper.like("name", brand.getName());
        }
            Page<Brand> brandPage = new Page<>(page, size);
        Page<Brand> page1 = brandMapper.selectPage(brandPage, queryWrapper);
        return page1;
        }
    }