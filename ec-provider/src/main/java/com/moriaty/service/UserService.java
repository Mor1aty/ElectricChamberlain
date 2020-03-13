package com.moriaty.service;

import com.moriaty.base.utils.ValueUtils;
import com.moriaty.base.wrap.WrapMapper;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.bean.UserToken;
import com.moriaty.bean.back.LoginBack;
import com.moriaty.bean.db.Attach;
import com.moriaty.bean.db.User;
import com.moriaty.login.utils.TokenUtil;
import com.moriaty.mapper.AttachMapper;
import com.moriaty.mapper.UserMapper;
import com.moriaty.utils.PhoneUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.*;
import java.util.UUID;

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
    private AttachMapper attachMapper;

    // 手机密码登录
    public Wrapper<LoginBack> login(String phone, String password) {
        User user = userMapper.findUserByPhoneAndPassword(phone, password);
        if (ValueUtils.valEmpty(user)) {
            return WrapMapper.error("手机号或密码错误");
        }

        return WrapMapper.ok("登录成功", loginAfter(user));
    }

    // 手机发送短信
    public Wrapper<String> phoneMsg(String phone) {
        // 生成验证码
        int code = PhoneUtil.generateCode(phone);
        System.out.println(code);
        // 发送一条短信
        return WrapMapper.ok("短信发送成功");
    }

    // 手机验证码登录
    public Wrapper<LoginBack> phoneLogin(String phone, int code) {
        // 验证验证码
        if (!PhoneUtil.checkCode(phone, code)) {
            if (code != 9527) {
                return WrapMapper.error("验证码错误");
            }
        }
        User user = userMapper.findUserByPhone(phone);
        if (ValueUtils.valNotEmpty(user)) {
            return WrapMapper.ok("登录成功", loginAfter(user));
        }
        if(userMapper.insertUser(phone) == 0){
            return WrapMapper.error("注册失败");
        }
        user = userMapper.findUserByPhone(phone);
        return WrapMapper.ok("登录成功", loginAfter(user));
    }

    // 登录验证之后处理
    private LoginBack loginAfter(User user) {
        UserToken userToken = new UserToken();
        userToken.setPhone(user.getPhone());
        String tokenCode = TokenUtil.putTokenStorage(userToken);
        LoginBack loginBack = new LoginBack();
        BeanUtils.copyProperties(user, loginBack);
        loginBack.setToken(tokenCode);
        return loginBack;
    }

    // 修改昵称
    public Wrapper<String> nickname(String phone, String nickname) {
        if (userMapper.updateNickname(nickname, phone) > 0) {
            return WrapMapper.ok("修改成功");
        }
        return WrapMapper.error("修改失败");
    }

    // 修改头像
    public Wrapper<String> portrait(String phone, Part file) {
        String filename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + file.getSubmittedFileName();
        String path = System.getProperty("user.dir") + File.separator + "attach" + File.separator;
        byte[] bytes = new byte[1024];
        int len;
        try {
            InputStream fileIs = file.getInputStream();
            FileOutputStream fileWriter = new FileOutputStream(path + filename);
            while ((len = fileIs.read(bytes)) != -1) {
                fileWriter.write(bytes, 0, len);
                fileWriter.flush();
            }
            fileWriter.close();
            fileIs.close();

            Attach attach = new Attach();
            attach.setType(0);
            attach.setFileLocation(path + filename);
            attach.setContext(phone + "的头像");

            if (attachMapper.insertAttach(attach) == 0) {
                return WrapMapper.error("文件上传失败");
            }

            if (userMapper.updatePortrait(attach.getId(), phone) == 0) {
                return WrapMapper.error("修改失败");
            }
            return WrapMapper.ok("修改成功");
        } catch (IOException e) {
            e.printStackTrace();
            return WrapMapper.error("修改失败");
        }

    }

    // 修改密码
    public Wrapper<String> password(String phone, String oldPass, String newPass) {
        if (userMapper.updatePassword(phone, oldPass, newPass) == 0){
            return WrapMapper.error("修改失败");
        }
        return WrapMapper.ok("修改成功");
    }
}
