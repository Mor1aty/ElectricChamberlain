package com.moriaty.bean;

import com.moriaty.login.storage.Token;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/14 15:13
 * @Description TODO
 * 登录 token
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserToken extends Token {
    private String phone;
}
