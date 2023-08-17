package com.atbanfei.ssm.service;

import com.atbanfei.ssm.dao.ClassFeeDao;
import com.atbanfei.ssm.pojo.ClassFee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;





@Service
public class ClassFeeService {
    @Autowired
   private ClassFeeDao classFeeDao;

    /**
     * 查询所有班级收取班费信息
     * 全班待收一次班费为一条记录
     * 例如：全班每人收取5元，共五人
     * 那么这条记录为一条收取25元的班费记录，以班级为单位
     * @return
     */
    public List<ClassFee> selectAllClassFee(){
        List<ClassFee>  ClassFee= classFeeDao.selectAllClassFee();
        return ClassFee;
    }

    /**
     * 根据班费id查询班费信息
     * @param f_id
     * @return
     */
    public ClassFee getClassFeeById(Integer f_id){
        return classFeeDao.getClassFeeById(f_id);
    }

    /**
     * 新增班费
     * @param classFee
     * @return
     */
    public boolean insertClassFee(ClassFee classFee){
        try{
            classFeeDao.insertClassFee(classFee);
            return true;
            /*DuplicateKeyException异常处理:java向数据库插入数据异常*/
        }catch (DuplicateKeyException e){
            return false;
        }
    }

    /**
     * 编辑班费
     * @param classFee
     * @return
     */
    public boolean updateClassFee(ClassFee classFee){
        try{
            classFeeDao.updateClassFee(classFee);
            return true;
            /*DuplicateKeyException异常处理:java向数据库插入数据异常*/
        }catch (DuplicateKeyException e){
            return false;
        }
    }

    /**
     * 删除班费
     * @param f_id
     * @return
     */
    public boolean deleteClassFee(Integer f_id){
       int r = classFeeDao.deleteClassFee(f_id);
       if(r>0){
           return true;
       }else{
           return false;
       }
    }

    /**
     * 模糊查询
     * @param f_title
     * @return
     */
    public List<ClassFee> searchTitle(String f_title){
        List<ClassFee> classFees= classFeeDao.searchTitle(f_title);
        return classFees;
    }

    /**
     * 统计需交班费
     * @return
     */
    public Double sumFee(){
        Double r = classFeeDao.sumFee();
        return r;
    }
}
