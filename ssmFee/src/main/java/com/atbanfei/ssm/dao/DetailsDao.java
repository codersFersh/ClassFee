package com.atbanfei.ssm.dao;



import com.atbanfei.ssm.pojo.Details;

import java.util.List;

public interface DetailsDao {

    /**
     * 查询所有学生缴费
     * @return
     */
    List<Details> selectAllDetails(Integer f_id);

    /**
     * 根据id查询缴费信息
     * @param d_id
     * @return
     */
    Details getDetailsById(Integer d_id);

    /**
     * 新增缴费
     * @param details
     * @return
     */
    int insertDetails(Details details);

    /**
     * 编辑缴费
     * @param details
     * @return
     */
    int updateDetails(Details details);

    /**
     * 删除缴费
     * @param d_id
     * @return
     */
    int deleteDetails(Integer d_id);

    /**
     * 查询id,防止classFeeContents有数据的记录
     * @param f_id
     * @return
     */
     Integer selectFid(Integer f_id);


     /**
     * 查询id,防止StudentContents有数据的记录
     * @param s_id
     * @return
     */
     Integer selectSid(Integer s_id);

    /**
     * 统计已缴班费
     * @return
     */
     Double sumMoney();

    /**
     * 查询一条班费记录中的缴费记录，前端做按钮禁用需要
     * @param f_id
     * @return
     */
     Double sumFidMoney(Integer f_id);
}
