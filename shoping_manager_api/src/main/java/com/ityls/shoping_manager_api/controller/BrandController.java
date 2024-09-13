package com.ityls.shoping_manager_api.controller;

//import com.ityls.shoping_common.pojo.Brand;
//import com.ityls.shoping_common.result.BaseResult;
//import com.ityls.shoping_common.service.BrandService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ityls.shoping_common.pojo.*;
import com.ityls.shoping_common.result.*;
import com.ityls.shoping_common.service.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
//     远程注入
    @DubboReference
    private BrandService brandService;

    @PostMapping("/add")
    public BaseResult add(@RequestBody Brand brand) {
        brandService.add(brand);
        return BaseResult.ok();
    }

    @DeleteMapping("/delete")
    public BaseResult delete(Long id) {
        brandService.delete(id);
        return BaseResult.ok();
    }

    @PutMapping("/update")
    public BaseResult update(@RequestBody Brand brand) {
        brandService.update(brand);
        return BaseResult.ok();
    }

    @GetMapping("/findById")
    public BaseResult findById(Long id){
        Brand brand = brandService.findById(id);
        return BaseResult.ok(brand);
    }

    @GetMapping("/all")
    public BaseResult findAll(){
        List<Brand> brands = brandService.findAll();
        return BaseResult.ok(brands);
    }

    @GetMapping("/search")
    public BaseResult<Page<Brand>> search(Brand brand,int page,int size){
        Page<Brand> search = brandService.search(brand,page, size);
        return BaseResult.ok(search);
    }
}