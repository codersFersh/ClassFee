package com.atbanfei.ssm.dao;

import com.atbanfei.ssm.pojo.ClassFee;
import com.atbanfei.ssm.pojo.Expend;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ExpendDao {
    /**
     * 查询所有班费支出信息
     * @return
     */
    List<Expend> selectAllExpend();

    /**
     * 根据id查询班费支出信息
     * @param e_id
     * @return
     */
    Expend getExpendById(Integer e_id);

    /**
     * 新增班费支出
     * @param expend
     * @return
     */
    int insertExpend(Expend expend);

    /**
     * 编辑班费支出
     * @param expend
     * @return
     */
    int updateExpend(Expend expend);

    /**
     * 删除一条班费支出记录
     * @param e_id
     * @return
     */
    int deleteExpend(Integer e_id);

    /**
     * 模糊查询
     * @param e_title
     * @return
     */
    List<Expend>searchTitle(@Param("e_title") String e_title);

    Double sumGross();
}
