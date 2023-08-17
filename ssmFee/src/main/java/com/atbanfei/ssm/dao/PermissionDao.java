package com.atbanfei.ssm.dao;

import com.atbanfei.ssm.pojo.Permission;

import java.util.List;



public interface PermissionDao {

    /**
     * 查询所有使用权限信息
     * @return
     */
    List<Permission> selectAllPermission();

    /**
     * 根据班费id查询班费信息
     * @param p_id
     * @return
     */
    Permission getClassFeeById(Integer p_id);

    /**
     * 新增班费
     * @param permission
     * @return
     */
    int insertPermission(Permission permission);

    /**
     * 编辑班费
     * @param permission
     * @return
     */
    int updatePermission(Permission permission);

    /**
     * 删除一条班费记录
     * @param p_id
     * @return
     */
    int deletePermission(Integer p_id);
}
