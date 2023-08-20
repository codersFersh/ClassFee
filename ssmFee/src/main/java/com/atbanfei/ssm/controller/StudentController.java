package com.atbanfei.ssm.controller;

import com.atbanfei.ssm.pojo.Student;
import com.atbanfei.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Autowired是一种注解，可以对成员变量、
 * 方法和构造函数进行标注，来完成自动装配的工作，
 * @Autowired标注可以放在成员变量上，
 * 也可以放在成员变量的set方法上，也
 * 可以放在任意方法上表示，自动执行当前方法
 * ，如果方法有参数，会在IOC容器中自动寻找同类型参数为其传值。
 */

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
/*映射地址*/
@RequestMapping("Student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*查询所有学生*/
    @RequestMapping(value = "stuAll",method = RequestMethod.GET)
    @ResponseBody
    public List<Student> GetSelectAllStudent(){
        List<Student> datas=studentService.selectAllStudent();
        System.out.println(datas);
        return  datas;
    }

    /*根据姓名模糊查询*/
    @RequestMapping(value = "stuName",method = RequestMethod.GET)
    @ResponseBody
    public List<Student> searchStuName(@RequestParam("s_name") String s_name) {
        List<Student> datas=studentService.searchStuName(s_name);
        System.out.println(datas);
        return  datas;
    }


    /*根据学号查询*/
    @RequestMapping(value = "stuId",method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentById(@RequestParam("s_id") Integer s_id) {
        Student student=studentService.getStudentById(s_id);
        return student;
    }

    /*添加学生*/
    @RequestMapping(value = "stuAdd", method = RequestMethod.POST)
    @ResponseBody
    public boolean PostInsertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }




    /*修改学生*/
    @RequestMapping(value = "stuEdit",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostUpdateStudent(@RequestBody Student student ){
        return studentService.updateStudent(student);
//       if(student !=null){
//           boolean s =  studentService.updateStudent(student);
//           return s;
//       }else {
//           return false;
//       }

    }

    /*删除学生*/
    @RequestMapping(value = "/stuDel",method = RequestMethod.POST)
    @ResponseBody
    public boolean PostDeleteStudent(@RequestBody Student student, HttpServletRequest request){
        if(student.getS_id() !=null){
            boolean s =  studentService.deleteStudent(student.getS_id());
            return s;
        }else {
            return false;
        }

    }

    /*统计学生*/
    @RequestMapping(value = "/stuCount",method = RequestMethod.GET)
    @ResponseBody
    public Integer countStudent(){
        return studentService.countStudent();
    }

}
