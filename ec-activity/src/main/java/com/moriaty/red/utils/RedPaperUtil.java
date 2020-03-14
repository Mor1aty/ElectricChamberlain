package com.moriaty.red.utils;

import com.moriaty.red.storage.RedPaper;

import java.util.List;
import java.util.Random;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/14 15:19
 * @Description TODO
 * 红包工具类
 */
public class RedPaperUtil {

    // 根据活动生成红包
    public static void generateRedPaper(List<String> phones, long minMoney, long maxMoney) {
        Random random = new Random();
        for (String phone : phones) {
            // Math.random()*(n-m)+m
            RedPaper.put(phone, random.nextInt((int) maxMoney - (int) minMoney) + minMoney);
        }
    }

    // 获取红包
    public static long getRedPaper(String phone) {
        return RedPaper.get(phone);
    }

    // 移除红包
    public static void removeRedPaper(String phone) {
        RedPaper.remove(phone);
    }
}
