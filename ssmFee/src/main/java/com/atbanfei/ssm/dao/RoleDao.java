package com.atbanfei.ssm.dao;

import com.atbanfei.ssm.pojo.Role;

import java.util.List;



public interface RoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> selectAllRole();

    /**
     * 根据角色id查询角色信息
     * @param r_id
     * @return
     */
    Role getRoleById(Integer r_id);

    /**
     * 新增角色
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 编辑角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 删除角色
     * @param r_id
     * @return
     */
    int deleteRole(Integer r_id);

}
