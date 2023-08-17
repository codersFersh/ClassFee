package com.atbanfei.ssm.controller;


import com.atbanfei.ssm.pojo.Role;
import com.atbanfei.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
/*映射地址*/
@RequestMapping("Role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /*查询所有角色*/
    @RequestMapping(value = "roleAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> GetSelectAllRole(){
        List<Role> datas=roleService.selectAllRole();
        return  datas;
    }

    /*添加角色*/
    @RequestMapping(value = "/roleAdd",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostInsertRole(@RequestBody Role role ){
        boolean s =  roleService.insertRole(role);
        return s;
    }



    /*修改角色*/
    @RequestMapping(value = "/roleEdit",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostUpdateRole(@RequestBody Role role ){
        if(role !=null){
            boolean s =  roleService.updateRole(role);
            return s;
        }else {
            return false;
        }

    }

    /*删除角色*/
    @RequestMapping(value = "/roleDel",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostDeleteGrade(@RequestBody Role role, HttpServletRequest request){
        if(role.getR_id() !=null){
            boolean s =  roleService.deleteRole(role.getR_id());
            return s;
        }else {
            return false;
        }

    }
}
