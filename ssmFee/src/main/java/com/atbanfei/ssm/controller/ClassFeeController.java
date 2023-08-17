package com.atbanfei.ssm.controller;

import com.atbanfei.ssm.pojo.ClassFee;
import com.atbanfei.ssm.service.ClassFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("ClassFee")
public class ClassFeeController {
    @Autowired
    private ClassFeeService classFeeService;
    /*查询所有班费*/
    @RequestMapping(value = "cfAll",method = RequestMethod.GET)
    @ResponseBody
    public List<ClassFee> GetSelectAllClassFee(){
        List<ClassFee> datas=classFeeService.selectAllClassFee();
        System.out.println(datas);
        return  datas;
    }

    /*模糊查询*/
    @RequestMapping(value = "cfSearch",method = RequestMethod.GET)
    @ResponseBody
    public List<ClassFee>GetSearchTitle(@RequestParam("f_title") String f_title){
        List<ClassFee> datas = classFeeService.searchTitle(f_title);
        System.out.println(datas);
        return datas;
    }

    /*添加班费*/
    @RequestMapping(value = "cfAdd",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostInsertClassFee(@RequestBody ClassFee classFee){
        return classFeeService.insertClassFee(classFee);
    }

    /*编辑班费*/
    @RequestMapping(value = "cfEdit",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostUpdateClassFee(@RequestBody ClassFee classFee){
        return classFeeService.updateClassFee(classFee);
    }

    /*删除班费*/
    @RequestMapping(value = "cfDel",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostDeleteClassFee(@RequestBody ClassFee classFee, HttpServletRequest request) {
        if(classFee.getF_id() !=null){
            boolean s= classFeeService.deleteClassFee(classFee.getF_id());
            return s;
        }else {
            return false;
        }
    }

    /*根据id查询一条数据*/
    @RequestMapping(value = "cfId",method = RequestMethod.GET)
    @ResponseBody
    public ClassFee getClassFeeById(@RequestParam("f_id") Integer f_id){
        ClassFee classFee = classFeeService.getClassFeeById(f_id);
        return classFee;
    }

    /*统计计划收费*/
    @RequestMapping(value = "/sumFee",method = RequestMethod.GET)
    @ResponseBody
    public Double sumFee(){
        Double datas = classFeeService.sumFee();
        return datas;
    }

}
