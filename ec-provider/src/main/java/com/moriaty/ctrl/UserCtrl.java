package com.moriaty.ctrl;

import com.moriaty.bean.UserToken;
import com.moriaty.bean.back.LoginBack;
import com.moriaty.login.aspect.NeedLogin;
import com.moriaty.valid.aspect.ParamValidation;
import com.moriaty.valid.aspect.Param;
import com.moriaty.valid.bean.Method;
import com.moriaty.base.wrap.WrapParams;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/11 23:22
 * @Description TODO
 * 用户 Ctrl
 */
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserCtrl {

    private UserService userService;

    // 登录
    @PostMapping("login")
    @ParamValidation({@Param(value = "phone", method = Method.PHONE), @Param("password")})
    public Wrapper<LoginBack> login(WrapParams wrapParams) {
        return userService.login(wrapParams.getString("phone"), wrapParams.getString("password"));
    }

    // 手机发送短信
    @GetMapping("phone")
    @ParamValidation(@Param(value = "phone", method = Method.PHONE))
    public Wrapper<String> phoneMsg(WrapParams wrapParams) {
        return userService.phoneMsg(wrapParams.getString("phone"));
    }

    // 手机验证码登录
    @PostMapping("phone")
    @ParamValidation({@Param(value = "phone", method = Method.PHONE), @Param(value = "code", method = Method.NUMBER)})
    public Wrapper<LoginBack> phoneLogin(WrapParams wrapParams) {
        return userService.phoneLogin(wrapParams.getString("phone"), wrapParams.getIntValue("code"));
    }

    // 修改昵称
    @PutMapping("nickname")
    @NeedLogin("user")
    @ParamValidation(@Param("nickname"))
    public Wrapper<String> nickname(WrapParams wrapParams) {
        return userService.nickname(((UserToken) wrapParams.getTokenValue("user")).getPhone(), wrapParams.getString("nickname"));
    }

    // 修改头像
    @PutMapping("portrait")
    @NeedLogin("user")
    @ParamValidation(@Param("portrait"))
    public Wrapper<String> portrait(WrapParams wrapParams) {
        return userService.portrait(((UserToken) wrapParams.getTokenValue("user")).getPhone(), wrapParams.getPartValue("portrait"));
    }

    // 修改密码
    @PostMapping("password")
    @NeedLogin("user")
    @ParamValidation({@Param("oldPass"), @Param("newPass")})
    public Wrapper<String> password(WrapParams wrapParams) {
        return userService.password(((UserToken) wrapParams.getTokenValue("user")).getPhone(),
                wrapParams.getString("oldPass"), wrapParams.getString("newPass"));
    }
}
