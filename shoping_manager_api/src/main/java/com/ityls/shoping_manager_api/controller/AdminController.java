package com.ityls.shoping_manager_api.controller;

import java.util.Base64;
import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ityls.shoping_common.pojo.Admin;
import com.ityls.shoping_common.result.BaseResult;
import com.ityls.shoping_common.service.AdminService;
import com.ityls.shoping_manager_api.security.SecurityConfig;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    // 远程注入
    @Autowired
    private PasswordEncoder encoder;
    @DubboReference
    private AdminService adminService;

    @PostMapping("/add")
    public BaseResult add(@RequestBody Admin admin){
        String password = admin.getPassword();
        password = encoder.encode(password);
        admin.setPassword(password);
        adminService.add(admin);
        return BaseResult.ok();
    }

    @PutMapping("/update")
    public BaseResult update(@RequestBody Admin admin){
        String password = admin.getPassword();
        if (StringUtils.hasText(password)){ // 密码不为空加密
            password = encoder.encode(password);
            admin.setPassword(password);
        }
        adminService.update(admin);
        return BaseResult.ok();
    }


    @DeleteMapping("/delete")
    public BaseResult delete(Long aid){
        adminService.delete(aid);
        return BaseResult.ok();
    }
    @GetMapping("/findById")
    public BaseResult<Admin> findById(Long aid){
        Admin admin = adminService.findById(aid);
        return BaseResult.ok(admin);
    }
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('/admin/search')")
    public BaseResult<Page<Admin>> search(int page, int size){
        Page<Admin> adminPage = adminService.search(page, size);
        return BaseResult.ok(adminPage);
    }
    @PutMapping("/updateRoleToAdmin")
    public BaseResult updateRoleToAdmin(Long aid, Long[] rids){
        adminService.updateRoleToAdmin(aid, rids);
        return BaseResult.ok();
    }
    @GetMapping("/getUsername")
    public BaseResult<String> getUsername() {
        // 1.获取会话对象
        SecurityContext context = SecurityContextHolder.getContext();
        // 2.获取认证对象
        Authentication authentication = context.getAuthentication();
        // 3.获取登录用户信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return BaseResult.ok(username);
    }
}

