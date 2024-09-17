package com.ityls.shoping_category_customer_api.controller;


import com.ityls.shoping_common.pojo.Category;
import com.ityls.shoping_common.result.BaseResult;
import com.ityls.shoping_common.service.CategoryService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/category")
public class CategoryController {
    @DubboReference
    private CategoryService categoryService;

    @GetMapping("/all")
    public BaseResult<List<Category>> finfAll(){
        List<Category> categoryList = categoryService.findAll();
        return BaseResult.ok(categoryList);
    }
}
