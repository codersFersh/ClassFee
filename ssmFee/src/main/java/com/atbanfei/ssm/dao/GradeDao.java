package com.atbanfei.ssm.dao;

import com.atbanfei.ssm.pojo.Grade;

import java.util.List;



public interface GradeDao {

    /**
     * 查询所有班级
     * @return
     */
    List<Grade>selectAllGrade();

    /**
     * 根据班级编号查询班级信息
     * @param s_id
     * @return
     */
    Grade getGradeById(Integer s_id);

    /**
     * 新增角色
     * @param grade
     * @return
     */
    int insertGrade(Grade grade);

    /**
     * 编辑班级
     * @param grade
     * @return
     */
    int updateGrade(Grade grade);

    /**
     * 删除班级
     *
     * @param id
     * @return
     */
     int deleteGrade(Integer id);


}
