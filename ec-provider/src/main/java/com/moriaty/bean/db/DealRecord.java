package com.moriaty.bean.db;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/11 22:53
 * @Description TODO
 * 交易记录表
 */
@Data
public class DealRecord {
    private long id;
    private int type;
    private User payer;
    private User payee;
    private long amount;
    private String context;
    private LocalDateTime recordTime;
}
