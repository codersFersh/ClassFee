package com.atbanfei.ssm.service;

import com.atbanfei.ssm.dao.ExpendDao;
import com.atbanfei.ssm.pojo.Expend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;





@Service
public class ExpendService {

    @Autowired
    private   ExpendDao expendDao;

    /**
     * 根据查询支出班费
     * @return
     * @param
     */
    public List<Expend> selectAllExpend(){
        List<Expend> expend = expendDao.selectAllExpend();
        return  expend;
    }

    /**
     * 根据id获取一条记录
     * @param e_id
     * @return
     */
    public Expend getExpendById(Integer e_id){
        Expend expend = expendDao.getExpendById(e_id);
        return expend;
    }

    /**
     * 新增支出班费
     * @param expend
     * @return
     */
    public boolean insertExpend(Expend expend){
        try {
            expendDao.insertExpend(expend);
            return true;
            /*DuplicateKeyException异常处理:java向数据库插入数据异常*/
        } catch (DuplicateKeyException e) {
            // 如果是重复数据，则返回false
            return false;
        }
    }

    /**
     * 编辑支出班费
     * @param expend
     * @return
     */
    public boolean updateExpend(Expend expend){
        try{
            expendDao.updateExpend(expend);
            return true;
            /*DuplicateKeyException异常处理:java向数据库插入数据异常*/
        }catch (DuplicateKeyException e ){
            return false;
        }
    }

    /**
     * 删除支出班费
     * @param e_id
     * @return
     */
    public boolean deleteExpend(Integer e_id){
        int r = expendDao.deleteExpend(e_id);
        if(r>0){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 模糊查询
     * @param e_title
     * @return
     */
    public List<Expend> searchTitle(String e_title){
        List<Expend> expend= expendDao.searchTitle(e_title);
        return expend;
    }

    public Double sumGross(){
        Double r =  expendDao.sumGross();
        return r;
    }
}
