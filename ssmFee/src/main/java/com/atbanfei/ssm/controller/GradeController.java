package com.atbanfei.ssm.controller;

import com.atbanfei.ssm.pojo.Grade;
import com.atbanfei.ssm.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
/*映射地址*/
@RequestMapping("Grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    /*查询所有班级*/
    @RequestMapping(value = "graAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Grade> GetSelectAllGrade(){
        List<Grade> datas=gradeService.selectAllGrade();
        return  datas;
    }

    /*添加班级*/
    @RequestMapping(value = "/graAdd",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostInsertGrade(@RequestBody Grade grade ){
        boolean s =  gradeService.insertGrade(grade);
        return s;
    }



    /*修改班级*/
    @RequestMapping(value = "/graEdit",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostUpdateGrade(@RequestBody Grade grade ){
        if(grade !=null){
            boolean s =  gradeService.updateGrade(grade);
            return s;
        }else {
            return false;
        }

    }

    /*删除班级*/
    @RequestMapping(value = "/graDel",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostDeleteGrade(@RequestBody Grade grade, HttpServletRequest request){
        if(grade.getG_id() !=null){
            boolean s =  gradeService.deleteGrade(grade.getG_id());
            return s;
        }else {
            return false;
        }

    }
}
