package com.moriaty.bean.db;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/11 22:50
 * @Description TODO
 * 申请表
 */
@Data
public class Apply {
    private long id;
    private User user;
    private int type;
    private String context;
    private int isPass;
    private LocalDateTime applyTime;

}
