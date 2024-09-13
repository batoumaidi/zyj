package com.ityls.shoping_admin_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ityls.shoping_common.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    // 删除角色_权限表中的相关数据
    void deletePermissionAllRole(Long pid);
}