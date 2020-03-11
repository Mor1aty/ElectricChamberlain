package com.moriaty.bean.db;

import lombok.Data;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/11 22:49
 * @Description TODO
 * 附件表
 */
@Data
public class Attach {
    private long id;
    private int type;
    private String fileLocation;
    private String context;
}
