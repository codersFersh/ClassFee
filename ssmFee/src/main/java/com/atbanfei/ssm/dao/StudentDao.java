package com.atbanfei.ssm.dao;

import com.atbanfei.ssm.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.UserException;

import java.util.List;
import java.util.Map;



public interface StudentDao {

    /**
     * 查询所有学生
     * @return
     */
    List<Student>selectAllStudent();

    /**
     * 根据学号查询学生信息
     * @param s_id
     * @return
     */
    Student getStudentById(Integer s_id);

    /**
     * 新增学生
     * @param student
     * @return
     */
    int insertStudent(Student student);

    /**
     * 编辑学生
     * @param student
     * @return
     */
    int updateStudent(Student student);

    /**
     * 删除学生
     * @param s_id
     * @return
     */
    int deleteStudent(Integer s_id);

    /**
     * 根据学生姓名进行模糊查询
     * @param s_name
     * @return
     */
    List<Student> searchStuName(@Param("s_name") String s_name);

    /**
     * 计算学生总数
     * @return
     */
    Integer countStudent();

    /**
     * 用户登录
     * @param
     * @param
     * @return
     */
    Student LoginUser(Student student);
    
}
