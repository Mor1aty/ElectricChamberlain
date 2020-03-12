package com.moriaty.login.storage;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/12 22:46
 * @Description TODO
 * Token 存储集合
 */
public class Storage {
    private static HashMap<String, Token> storage = new HashMap<>();

    // 存入 Token
    public static void put(String tokenCode, Token token) {
        storage.put(tokenCode, token);
    }

    // 获取 Token
    public static Token get(String tokenCode) {
        return storage.get(tokenCode);
    }

    // 删除 Token
    public static void remove(String tokenCode) {
        storage.remove(tokenCode);
    }
}
