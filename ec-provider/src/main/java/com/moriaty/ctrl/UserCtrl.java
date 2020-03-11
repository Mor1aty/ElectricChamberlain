package com.moriaty.ctrl;

import com.moriaty.auth.aspect.Authentication;
import com.moriaty.auth.aspect.Param;
import com.moriaty.auth.bean.Method;
import com.moriaty.base.utils.ValueUtils;
import com.moriaty.base.wrap.WrapMapper;
import com.moriaty.base.wrap.WrapParams;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.bean.db.User;
import com.moriaty.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/11 23:22
 * @Description TODO
 * 用户 Ctrl
 */
@RestController
@AllArgsConstructor
public class UserCtrl {

    private UserService userService;

    // 登录
    @PostMapping("login")
    @Authentication({@Param(value = "phone", method = Method.PHONE), @Param("password")})
    public Wrapper<User> login(WrapParams wrapParams) {
        User user = userService.getUserByPhoneAndPassword(wrapParams.getString("phone"), wrapParams.getString("password"));
        if (ValueUtils.valEmpty(user)) {
            return WrapMapper.error("手机号或密码错误");
        }
        return WrapMapper.ok("登录成功", user);
    }

}
