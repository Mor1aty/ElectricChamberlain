package com.moriaty.bean.db;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/11 22:55
 * @Description TODO
 * 电价表
 */
@Data
public class ElectricityPrice {
    private long id;
    private long ordinaryMoney;
    private long discountMoney;
    private String city;
    private LocalDateTime priceTime;
}
