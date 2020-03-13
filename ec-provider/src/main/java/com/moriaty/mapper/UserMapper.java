package com.moriaty.mapper;

import com.moriaty.bean.db.User;
import org.apache.ibatis.annotations.*;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/11 23:01
 * @Description TODO
 * 用户 Mapper
 */
public interface UserMapper {
    // 根据手机和密码获取用户
    @Select("SELECT phone, password, nickname, money, portrait, type " +
            "FROM user " +
            "WHERE phone = #{phone} AND password = #{password}")
    @Results({
            @Result(property = "portrait", column = "portrait", one = @One(select = "com.moriaty.mapper.AttachMapper.findAttachById"))
    })
    User findUserByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);

    // 根据手机获取用户
    @Select("SELECT phone, password, nickname, money, portrait, type " +
            "FROM user " +
            "WHERE phone = #{phone}")
    @Results({
            @Result(property = "portrait", column = "portrait", one = @One(select = "com.moriaty.mapper.AttachMapper.findAttachById"))
    })
    User findUserByPhone(@Param("phone") String phone);

    // 生成用户
    @Insert("INSERT INTO user(phone,nickname) VALUES(#{phone},#{phone})")
    int insertUser(@Param("phone") String phone);

    // 修改昵称
    @Update("UPDATE user SET nickname = #{nickname} WHERE phone = #{phone}")
    int updateNickname(@Param("nickname") String nickname, @Param("phone") String phone);

    // 修改头像
    @Update("UPDATE user SET portrait = #{portrait} WHERE phone = #{phone}")
    int updatePortrait(@Param("portrait") long portrait, @Param("phone") String phone);

    // 修改密码
    @Update("UPDATE user SET password = #{newPass} WHERE phone = #{phone} AND password = #{oldPass}")
    int updatePassword(@Param("phone") String phone, @Param("oldPass") String oldPass, @Param("newPass") String newPass);

}
