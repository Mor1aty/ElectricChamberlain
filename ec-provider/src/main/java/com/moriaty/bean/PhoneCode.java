package com.moriaty.bean;

import lombok.Data;

import java.time.LocalDateTime;
/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2020/3/14 15:13
 * @Description TODO
 *   手机验证码
 */
@Data
public class PhoneCode {
    // 验证码
    private int code;
    // 有效时间，单位:秒
    private long effectiveTime;
    // 生成时间
    private LocalDateTime generationTime;
}
