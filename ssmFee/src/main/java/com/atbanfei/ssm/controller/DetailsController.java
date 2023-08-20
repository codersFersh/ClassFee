package com.atbanfei.ssm.controller;

import com.atbanfei.ssm.pojo.Details;
import com.atbanfei.ssm.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
//@RequirePermission("dept:del")
@RequestMapping("Details")
public class DetailsController {

    /*spring中@Autowired注解，是用来自动装配对象的*/
    @Autowired
    private DetailsService detailsService;

    /*查询缴费信息*/
    @RequestMapping(value = "detAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Details>GetSelectAllDetails(Integer f_id){
        List<Details> datas = detailsService.selectAllDetails(f_id);
        System.out.println(datas);
        return datas;
    }


    /*添加缴费*/
    @RequestMapping(value = "detAdd", method = RequestMethod.POST)
    @ResponseBody
    public boolean PostInsertDetails(@RequestBody Details details) {
        return detailsService.insertDetails(details);
    }

    /*编辑缴费*/
    @RequestMapping(value = "detEdit",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostUpdateDetails(@RequestBody Details details){
        return detailsService.updateDetails(details);
    }


    /*删除缴费*/
    @RequestMapping(value = "detDel",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostDeleteDetails(@RequestBody Details details){
        if(details.getD_id()!=null){
            boolean s = detailsService.deleteDetails(details.getD_id());
            return s;
        }else {
            return false;
        }
    }

    @GetMapping("checkFid")
    @ResponseBody
    public Integer selectFid(@RequestParam("f_id") Integer f_id){
        Integer datas = detailsService.selectFid(f_id);
        return datas;
    }


    @GetMapping("checkSid")
    @ResponseBody
    public Integer selectSid(@RequestParam("s_id") Integer s_id){
        Integer datas = detailsService.selectSid(s_id);
        return datas;
    }

    @RequestMapping(value = "/sumMoney",method = RequestMethod.GET)
    @ResponseBody
    public Double countStudent(){
        Double datas = detailsService.sumMoney();
        return datas;
    }

    @RequestMapping(value = "/sumFidMoney",method = RequestMethod.GET)
    @ResponseBody
    public Double sumFidMoney(Integer f_id){
        Double datas = detailsService.sumFidMoney(f_id);
        return datas;
    }
}
