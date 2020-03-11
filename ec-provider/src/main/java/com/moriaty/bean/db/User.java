package com.moriaty.bean.db;

import lombok.Data;
/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2020/3/11 22:50
 * @Description TODO
 *   用户表
 */
@Data
public class User {
    private String phone;
    private String nickname;
    private long money;
    private Attach portrait;
    private int type;
}
