package com.moriaty.login.utils;

import com.moriaty.base.constant.CustomConstant;
import com.moriaty.base.utils.ValueUtils;
import com.moriaty.login.storage.Storage;
import com.moriaty.login.storage.Token;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/12 22:25
 * @Description TODO
 * Token 工具类
 */
public class TokenUtil {

    // 获取 request 的 Token
    public static Token checkToken(String tokenCode) {
        Token token = Storage.get(tokenCode);
        if (ValueUtils.valEmpty(token) || LocalDateTime.now().isBefore(token.generationTime.plusHours(token.effectiveTime))) {
            // Token 未找到或过期
            Storage.remove(tokenCode);
            return null;
        }
        return token;
    }

    // 存入 Token
    public static String putTokenStorage(Token token) {
        token.generationTime = LocalDateTime.now();
        if (ValueUtils.valEmpty(token.effectiveTime)) {
            token.effectiveTime = 5;
        }
        String tokenCode = UUID.randomUUID().toString().replaceAll("-", "");
        Storage.put(tokenCode, token);
        return tokenCode;
    }

}
