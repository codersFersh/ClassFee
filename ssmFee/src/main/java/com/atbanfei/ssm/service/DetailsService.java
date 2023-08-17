package com.atbanfei.ssm.service;

import com.atbanfei.ssm.dao.DetailsDao;
import com.atbanfei.ssm.pojo.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class DetailsService {
    @Autowired
    private DetailsDao detailsDao;

    /**
     * 根据id查询缴费记录
     * @return
     * @param f_id
     */
   public List<Details> selectAllDetails(Integer f_id){
       List<Details> details = detailsDao.selectAllDetails(f_id);
       return  details;
   }

    /**
     * 新增缴费记录
     * @param details
     * @return
     */
   public boolean insertDetails(Details details){
       try {
           detailsDao.insertDetails(details);
           return true;
           /*DuplicateKeyException异常处理:java向数据库插入数据异常*/
       } catch (DuplicateKeyException e) {
           // 如果是重复数据，则返回false
           return false;
       }
   }

    /**
     * 编辑缴费
     * @param details
     * @return
     */
   public boolean updateDetails(Details details){
       try{
           detailsDao.updateDetails(details);
           return true;
           /*DuplicateKeyException异常处理:java向数据库插入数据异常*/
       }catch (DuplicateKeyException e ){
           return false;
       }
   }

    /**
     * 删除缴费
     * @param d_id
     * @return
     */
   public boolean deleteDetails(Integer d_id){
       int r = detailsDao.deleteDetails(d_id);
       if(r>0){
           return true;
       }else{
           return false;
       }
   }

    /**
     * 统计
     * @param f_id
     * @return
     */
    public Integer  selectFid(Integer f_id){
        Integer r = detailsDao.selectFid(f_id);
        return r;
    }
    /**
     * 统计
     * @param s_id
     * @return
     */
    public Integer  selectSid(Integer s_id){
        Integer r = detailsDao.selectSid(s_id);
        return r;
    }

    /**
     * 统计缴费
     * @return
     */
    public Double sumMoney(){
        Double r = detailsDao.sumMoney();
        return r;
    }

    /**
     * 查询一条班费记录中的缴费记录，前端做按钮禁用需要
     * @param f_id
     * @return
     */
    public Double sumFidMoney(Integer f_id){
        Double r = detailsDao.sumFidMoney(f_id);
        return r;
    }
}
