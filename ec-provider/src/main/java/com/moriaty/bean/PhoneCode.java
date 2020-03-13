package com.moriaty.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PhoneCode {
    // 验证码
    private int code;
    // 有效时间，单位:秒
    private long effectiveTime;
    // 生成时间
    private LocalDateTime generationTime;
}
