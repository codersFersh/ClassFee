package com.atbanfei.ssm.service;




import com.atbanfei.ssm.dao.RoleDao;
import com.atbanfei.ssm.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> selectAllRole(){
        List<Role>  role= roleDao.selectAllRole();
        return role;
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    public boolean insertRole(Role role){
        int r= roleDao.insertRole(role);
        if(r>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Role getRoleById(Integer r_id){
        return roleDao.getRoleById(r_id);
    }

    /**
     * 编辑班级
     * @param role
     * @return
     */
    public boolean updateRole(Role role){
        int r= roleDao.updateRole(role);
        if(r>0){
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * 删除班级
     * @param r_id
     * @return
     */
    public boolean deleteRole(Integer r_id){
        int r= roleDao.deleteRole(r_id);
        if(r>0){
            return true;
        }
        else {
            return false;
        }
    }
}
