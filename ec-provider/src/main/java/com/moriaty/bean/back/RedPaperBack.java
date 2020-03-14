package com.moriaty.bean.back;

import com.moriaty.bean.db.User;
import lombok.Data;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/14 15:12
 * @Description TODO
 * 红包返回
 */
@Data
public class RedPaperBack {
    private long money;
    private User payer;
}
