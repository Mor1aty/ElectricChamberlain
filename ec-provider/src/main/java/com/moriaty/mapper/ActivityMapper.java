package com.moriaty.mapper;

import com.moriaty.bean.db.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2020/3/14 15:13
 * @Description TODO
 *   活动 Mapper
 */
public interface ActivityMapper {

    // 获取红包活动
    @Select("SELECT id, type, money_section, is_end, activity_time FROM activity WHERE type = 0")
    Activity findRedPaperActivity();

    // 活动过期
    @Update("UPDATE activity SET is_end = 1 WHERE id = #{id}")
    int updateActivityIsEnd(@Param("id") long id);
}
