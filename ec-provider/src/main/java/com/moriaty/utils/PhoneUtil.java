package com.moriaty.utils;

import com.moriaty.base.utils.ValueUtils;
import com.moriaty.bean.PhoneCode;
import com.moriaty.login.storage.Storage;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/13 17:25
 * @Description TODO
 * 手机工具类
 */
public class PhoneUtil {

    private static HashMap<String, PhoneCode> storage = new HashMap<>();

    // 生成验证码
    public static int generateCode(String phone) {
        PhoneCode phoneCode = new PhoneCode();
        // 生成四位验证码
        int code = (int) ((Math.random() * 9 + 1) * 1000);
        phoneCode.setCode(code);
        phoneCode.setEffectiveTime(60);
        phoneCode.setGenerationTime(LocalDateTime.now());
        storage.put(phone, phoneCode);
        return code;
    }

    // 检查验证码
    public static boolean checkCode(String phone, int code) {
        PhoneCode phoneCode = storage.get(phone);
        if (ValueUtils.valEmpty(phoneCode)) {
            return false;
        }
        if (LocalDateTime.now().isAfter(phoneCode.getGenerationTime().plusSeconds(phoneCode.getEffectiveTime()))) {
            // 验证码过期
            storage.remove(phone);
            return false;
        }
        if (code != phoneCode.getCode()) {
            // 验证码错误
            storage.remove(phone);
            return false;
        }
        return true;
    }

}
