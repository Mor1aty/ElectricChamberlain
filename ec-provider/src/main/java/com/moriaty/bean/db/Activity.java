package com.moriaty.bean.db;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/11 22:57
 * @Description TODO
 * 活动表
 */
@Data
public class Activity {
    private long id;
    private int type;
    private String moneySection;
    private int isEnd;
    private LocalDateTime activityTime;
}
