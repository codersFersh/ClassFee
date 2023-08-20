package com.atbanfei.ssm.service;

import com.atbanfei.ssm.dao.GradeDao;
import com.atbanfei.ssm.pojo.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class GradeService {
    @Autowired
    private GradeDao gradeDao;

    /**
     * 查询所有班级
     * @return
     */
    public List<Grade> selectAllGrade(){
        List<Grade>  grade= gradeDao.selectAllGrade();
        return grade;
    }

    /**
     * 新增班级
     * @param grade
     * @return
     */
    public boolean insertGrade(Grade grade){
        int r= gradeDao.insertGrade(grade);
        if(r>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Grade getGradeById(Integer g_id){
        return gradeDao.getGradeById(g_id);
    }

    /**
     * 编辑班级
     * @param grade
     * @return
     */
    public boolean updateGrade(Grade grade){
        int r= gradeDao.updateGrade(grade);
        if(r>0){
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * 删除班级
     * @param g_id
     * @return
     */
    public boolean deleteGrade(Integer g_id){
        int r= gradeDao.deleteGrade(g_id);
        if(r>0){
            return true;
        }
        else {
            return false;
        }
    }
}
