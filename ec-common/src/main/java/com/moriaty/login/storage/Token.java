package com.moriaty.login.storage;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/12 22:39
 * @Description TODO
 * Token bean
 */
public class Token {
    // 有效时间，单位:时
    public long effectiveTime;
    // 生成时间
    public LocalDateTime generationTime;

}
