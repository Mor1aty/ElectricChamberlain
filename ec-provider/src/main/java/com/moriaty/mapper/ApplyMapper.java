package com.moriaty.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/14 12:55
 * @Description TODO
 * 申请 Mapper
 */
public interface ApplyMapper {

    // 用户升级申请
    @Insert("INSERT INTO apply(user, type, context, is_pass, apply_time) VALUES(#{phone},0,#{context},0,#{applyTime})")
    int insertApply(@Param("phone") String phone, @Param("context") String context, @Param("applyTime") LocalDateTime applyTime);

}
