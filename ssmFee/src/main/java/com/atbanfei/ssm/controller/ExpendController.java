package com.atbanfei.ssm.controller;

import com.atbanfei.ssm.pojo.ClassFee;
import com.atbanfei.ssm.pojo.Expend;
import com.atbanfei.ssm.service.ClassFeeService;
import com.atbanfei.ssm.service.ExpendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("Expend")
public class ExpendController {
    @Autowired
    private ExpendService expendService;
    /*查询所有班费*/
    @RequestMapping(value = "expAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Expend> GetSelectAllClassFee(){
        List<Expend> datas=expendService.selectAllExpend();
        System.out.println(datas);
        return  datas;
    }



    /*添加班费*/
    @RequestMapping(value = "expAdd",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostInsertClassFee(@RequestBody Expend expend){
        return expendService.insertExpend(expend);
    }

    /*编辑班费*/
    @RequestMapping(value = "expEdit",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostUpdateClassFee(@RequestBody Expend expend){
        return expendService.updateExpend(expend);
    }

    /*删除班费*/
    @RequestMapping(value = "expDel",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostDeleteClassFee(@RequestBody Expend expend, HttpServletRequest request) {
        if(expend.getE_id() !=null){
            boolean s= expendService.deleteExpend(expend.getE_id());
            return s;
        }else {
            return false;
        }
    }

    /*根据id查询一条数据*/
    @RequestMapping(value = "expId",method = RequestMethod.GET)
    @ResponseBody
    public Expend getClassFeeById(@RequestParam("e_id") Integer e_id){
        Expend expend = expendService.getExpendById(e_id);
        return expend;
    }

    /*模糊查询*/
    @RequestMapping(value = "expSearch",method = RequestMethod.GET)
    @ResponseBody
    public List<Expend>GetSearchTitle(@RequestParam("e_title") String e_title){
        List<Expend> datas = expendService.searchTitle(e_title);
        System.out.println(datas);
        return datas;
    }

    @RequestMapping(value = "/sumGross",method = RequestMethod.GET)
    @ResponseBody
    public Double sumGross(){
        Double datas = expendService.sumGross();
        return datas;
    }
}
