package com.moriaty.ctrl;

import com.moriaty.bean.UserToken;
import com.moriaty.bean.back.LoginBack;
import com.moriaty.login.aspect.NeedLogin;
import com.moriaty.login.storage.Token;
import com.moriaty.login.utils.TokenUtil;
import com.moriaty.valid.aspect.ParamValidation;
import com.moriaty.valid.aspect.Param;
import com.moriaty.valid.bean.Method;
import com.moriaty.base.utils.ValueUtils;
import com.moriaty.base.wrap.WrapMapper;
import com.moriaty.base.wrap.WrapParams;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.bean.db.User;
import com.moriaty.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
    @ParamValidation({@Param(value = "phone", method = Method.PHONE), @Param("password")})
    public Wrapper<LoginBack> login(WrapParams wrapParams) {
        User user = userService.getUserByPhoneAndPassword(wrapParams.getString("phone"), wrapParams.getString("password"));
        if (ValueUtils.valEmpty(user)) {
            return WrapMapper.error("手机号或密码错误");
        }
        UserToken userToken = new UserToken();
        userToken.setPhone(user.getPhone());

        String tokenCode = TokenUtil.putTokenStorage(userToken);

        LoginBack loginBack = new LoginBack();
        BeanUtils.copyProperties(user, loginBack);
        loginBack.setToken(tokenCode);

        return WrapMapper.ok("登录成功", loginBack);
    }

    @GetMapping("test")
    @NeedLogin("user")
    @ParamValidation()
    public Wrapper<String> test(WrapParams wrapParams) {
        UserToken user = (UserToken) wrapParams.getTokenValue("user");
        System.out.println(user);
        return WrapMapper.ok("ok");
    }

}
