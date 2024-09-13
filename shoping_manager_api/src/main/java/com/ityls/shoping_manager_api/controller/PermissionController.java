package com.ityls.shoping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ityls.shoping_common.pojo.Permission;
import com.ityls.shoping_common.result.BaseResult;
import com.ityls.shoping_common.service.PermissionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    // 远程注入
    @DubboReference
    private PermissionService permissionService;


    @PostMapping("/add")
    public BaseResult add(@RequestBody Permission permission){
        permissionService.add(permission);
        return BaseResult.ok();
    }

    @PutMapping("/update")
    public BaseResult update(@RequestBody  Permission permission){
        permissionService.update(permission);
        return BaseResult.ok();
    }

    @DeleteMapping("/delete")
    public BaseResult delete(Long pid){
        permissionService.delete(pid);
        return BaseResult.ok();
    }

    @GetMapping("/findById")
    public BaseResult<Permission> findById(Long pid){
        Permission permission = permissionService.findById(pid);
        return BaseResult.ok(permission);
    }

    @GetMapping("/search")
    public BaseResult<Page<Permission>> search(int page, int size){
        Page<Permission> page1 = permissionService.search(page, size);
        return BaseResult.ok(page1);
    }

    @GetMapping("/findAll")
    public BaseResult<List<Permission>> findAll(){
        List<Permission> all = permissionService.findAll();
        return BaseResult.ok(all);
    }
}