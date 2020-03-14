package com.moriaty.red.storage;

import java.util.HashMap;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/14 15:12
 * @Description TODO
 * 红包
 */
public class RedPaper {
    private static HashMap<String, Long> storage = new HashMap<>();

    public static long get(String phone) {
        return storage.get(phone);
    }

    public static void put(String phone, long money) {
        storage.put(phone, money);
    }

    public static void remove(String phone) {
        storage.remove(phone);
    }
}
