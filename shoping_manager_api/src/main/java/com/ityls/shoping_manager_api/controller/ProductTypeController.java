package com.ityls.shoping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ityls.shoping_common.pojo.ProductType;
import com.ityls.shoping_common.result.BaseResult;
import com.ityls.shoping_common.service.ProductTypeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productType")
public class ProductTypeController {
    @DubboReference
    private ProductTypeService productTypeService;

    //添加商品类型
    @PostMapping("/add")
    public BaseResult addProductType(@RequestBody ProductType productType) {
        productTypeService.add(productType);
        return BaseResult.ok();
    }

    //删除商品类型
    @DeleteMapping("/delete")
    public BaseResult delete(Long id) {
        productTypeService.delete(id);
        return BaseResult.ok();
    }

    //修改商品类型
    @PutMapping("/update")
    public BaseResult update(@RequestBody ProductType productType) {
        productTypeService.update(productType);
        return BaseResult.ok();
    }

    //查询商品类型
    @GetMapping("/findById")
    public BaseResult findProductType(Long id) {
        ProductType productType = productTypeService.findById(id);
        return BaseResult.ok(productType);
    }

    //分页查询商品类型
    @GetMapping("/search")
    public BaseResult<Page<ProductType>> search(ProductType productType, int page, int size) {
        Page<ProductType> page1 = productTypeService.search(productType, page, size);
        return BaseResult.ok(page1);
    }
    @GetMapping("/findProductType")
    public BaseResult<List<ProductType>> findProductType(ProductType productType){
        List<ProductType> productType1 = productTypeService.findProductType(productType);
        return BaseResult.ok(productType1);
    }
    @GetMapping("findByParentId")
    public BaseResult<List<ProductType>> findByParentId(Long parentId){
        ProductType productType = new ProductType();
        productType.setParentId(parentId);
        List<ProductType> productType1 = productTypeService.findProductType(productType);
        return BaseResult.ok(productType1);
    }
}
