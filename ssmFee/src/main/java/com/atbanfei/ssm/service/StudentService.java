package com.atbanfei.ssm.service;

import com.atbanfei.ssm.dao.StudentDao;
import com.atbanfei.ssm.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *Server层
 */




@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    /**
     * 查询所有学生
     * @return
     */
    public List<Student> selectAllStudent(){
        List<Student>  student= studentDao.selectAllStudent();
        return student;
    }

    /**
     * 新增学生
     * @param student
     * @return
     */
    public boolean insertStudent(Student student) {
        try {
            studentDao.insertStudent(student);
            return true;
            /*DuplicateKeyException异常处理:java向数据库插入数据异常*/
        } catch (DuplicateKeyException e) {
            // 如果是重复数据，则返回false
            return false;
        }
    }

    /**
     * 根据id查询学生信息
     * @param s_id
     * @return
     */
    public Student getStudentById(Integer s_id){
        Student student = studentDao.getStudentById(s_id);
        return student;
    }


    /**
     * 编辑学生
     * @param student
     * @return
     */
    public boolean updateStudent(Student student){
        try {
            studentDao.updateStudent(student);
            return true;
            /*DuplicateKeyException异常处理:java向数据库插入数据异常*/
        } catch (DuplicateKeyException e) {
            // 如果是重复数据，则返回false
            return false;
        }
    }


    /**
     * 删除学生
     * @param s_id
     * @return
     */
    public boolean deleteStudent(Integer s_id){
        int r= studentDao.deleteStudent(s_id);
        if(r>0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 根据学生能够姓名进行模糊查询
     * @param s_name
     * @return
     */
    public List<Student> searchStuName(String s_name) {
        List<Student>  student=studentDao.searchStuName(s_name);
        return student;
    }

    /**
     * 计算学生总数
     * @return
     */
    public Integer countStudent(){
        return studentDao.countStudent();
    }

    /**
     * 用户登录
     * @param
     * @param
     */
    public Student LoginUser(Student student) throws Exception{
        //通过接口获取  s_phone s_password
        Student loginUser = studentDao.LoginUser(student);
        if(loginUser == null){
            //抛出异常
            throw new Exception("账号或者密码错误");
        } else if (!loginUser.getS_password().equals(student.getS_password())) {
            //抛出异常
            throw new Exception("账号或者密码错误");
        }
        return loginUser;
    }
}
