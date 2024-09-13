package com.ityls.shoping_goods_service.service;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ityls.shoping_common.pojo.ProductType;
import com.ityls.shoping_common.result.BusException;
import com.ityls.shoping_common.result.CodeEnum;
import com.ityls.shoping_common.service.ProductTypeService;
import com.ityls.shoping_goods_service.mapper.ProductTypeMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DubboService
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public void add(ProductType productType) {
        ProductType productTypeParent = productTypeMapper.selectById(productType.getParentId());
        if (productTypeParent == null) {
            productType.setLevel(1);
        } else if (productTypeParent.getLevel() < 3) {
            productType.setLevel(productTypeParent.getLevel() + 1);
        } else if (productTypeParent.getLevel() == 3) {
            throw new BusException(CodeEnum.INSERT_PRODUCT_TYPE_ERROR);
        }
        productTypeMapper.insert(productType);
    }

    @Override
    public void delete(Long id) {
        QueryWrapper<ProductType> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parentId", id);
        List<ProductType> productTypes = productTypeMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(productTypes)){
            // 删除商品类型异常
            // DELETE_PRODUCT_TYPE_ERROR(603, "此商品类型下面有子类型");
            throw new BusException(CodeEnum.DELETE_PRODUCT_TYPE_ERROR);
        }
        productTypeMapper.deleteById(id);
    }


    @Override
    public void update(ProductType productType) {
        ProductType productTypeParent = productTypeMapper.selectById(productType.getParentId());
        if (productTypeParent == null) {
            productType.setLevel(1);
        } else if (productTypeParent.getLevel() < 3) {
            productType.setLevel(productTypeParent.getLevel() + 1);
        } else if (productTypeParent.getLevel() == 3) {
            throw new BusException(CodeEnum.UPDATE_PRODUCT_TYPE_ERROR);
        }
        productTypeMapper.updateById(productType);
    }

    @Override
    public ProductType findById(Long id) {
        return productTypeMapper.selectById(id);
    }

    @Override
    public Page<ProductType> search(ProductType productType, int page, int size) {
        QueryWrapper<ProductType> queryWrapper = new QueryWrapper<>();
        // 类型名不为空时
        if (productType != null && productType.getName() != null) {
            queryWrapper.like("name", productType.getName());
        }
        // 上级类型id不为空时
        if (productType != null && productType.getParentId() != null) {
            queryWrapper.eq("parentId", productType.getParentId());
        }
        Page<ProductType> productTypePage = new Page<>(page, size);
        Page<ProductType> page1 = productTypeMapper.selectPage(productTypePage, queryWrapper);
        return page1;
    }

    @Override
    public List<ProductType> findProductType(ProductType productType) {
        QueryWrapper<ProductType> wrapper = new QueryWrapper<>();
        if (productType != null && productType.getName() != null) {
            wrapper.like("name", productType.getName());
        }
        if (productType != null && productType.getParentId() != null) {
            wrapper.eq("parentId", productType.getParentId());
        }
        return productTypeMapper.selectList(wrapper);
    }
}
