package com.moriaty.mapper;

import com.moriaty.bean.db.Attach;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/11 23:17
 * @Description TODO
 * 附件 Mapper
 */
public interface AttachMapper {
    @Select("SELECT id, type, file_location, context FROM attach WHERE id = #{id}")
    Attach findAttachById(@Param("id") long id);
}
