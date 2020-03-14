package com.moriaty.bean.back;

import com.moriaty.bean.db.Attach;
import lombok.Data;
/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2020/3/14 15:12
 * @Description TODO
 *   登录返回
 */
@Data
public class LoginBack {
    private String phone;
    private String nickname;
    private long money;
    private Attach portrait;
    private int type;

    private String token;
}
