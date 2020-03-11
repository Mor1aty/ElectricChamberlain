package com.moriaty.service;

import com.moriaty.bean.db.User;
import com.moriaty.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/11 23:19
 * @Description TODO
 * 用户 Service
 */
@Service
@AllArgsConstructor
public class UserService {

    private UserMapper userMapper;

    // 根据手机和密码获取用户
    public User getUserByPhoneAndPassword(String phone, String password) {
        return userMapper.findUserByPhoneAndPassword(phone, password);
    }
}
