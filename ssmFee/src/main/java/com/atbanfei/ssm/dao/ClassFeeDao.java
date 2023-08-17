package com.atbanfei.ssm.dao;



import com.atbanfei.ssm.pojo.ClassFee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassFeeDao {
    /**
     * 查询所有全班收费信息
     * @return
     */
    List<ClassFee> selectAllClassFee();

    /**
     * 根据班费id查询班费信息
     * @param f_id
     * @return
     */
    ClassFee getClassFeeById(Integer f_id);

    /**
     * 新增班费
     * @param classFee
     * @return
     */
    int insertClassFee(ClassFee classFee);

    /**
     * 编辑班费
     * @param classFee
     * @return
     */
    int updateClassFee(ClassFee classFee);

    /**
     * 删除一条班费记录
     * @param f_id
     * @return
     */
    int deleteClassFee(Integer f_id);

    /**
     * 模糊查询
     * @param f_title
     * @return
     */
    List<ClassFee>searchTitle(@Param("f_title") String f_title);

    /**
     * 统计班费
     * @return
     */
    Double sumFee();
}
