package com.ityls.shoping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ityls.shoping_common.pojo.Role;
import com.ityls.shoping_common.result.BaseResult;
import com.ityls.shoping_common.service.RoleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    // 远程注入
    @DubboReference
    private RoleService roleService;


    @PostMapping("/add")
    public BaseResult add(@RequestBody Role role){
        roleService.add(role);
        return BaseResult.ok();
    }

    @PutMapping("/update")
    public BaseResult update(@RequestBody Role role){
        roleService.update(role);
        return BaseResult.ok();
    }

    @DeleteMapping("/delete")
    public BaseResult delete(Long rid){
        roleService.delete(rid);
        return BaseResult.ok();
    }

    @GetMapping("/findById")
    public BaseResult<Role> findById(Long rid){
        Role role = roleService.findById(rid);
        return BaseResult.ok(role);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('/role/search')")
    public BaseResult<Page<Role>> search(int page, int size){
        Page<Role> rolePage = roleService.search(page, size);
        return BaseResult.ok(rolePage);
    }

    @GetMapping("/findAll")
    public BaseResult<List<Role>> findAll(){
        List<Role> all = roleService.findAll();
        return BaseResult.ok(all);
    }

    @PutMapping("/updatePermissionToRole")
    public BaseResult updatePermissionToRole(Long rid, Long[] pids){
        roleService.updatePermissionToRole(rid, pids);
        return BaseResult.ok();
    }
}