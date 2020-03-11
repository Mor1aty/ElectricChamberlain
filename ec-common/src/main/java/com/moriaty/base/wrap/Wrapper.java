package com.moriaty.base.wrap;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2019/11/15 10:15
 * @Description TODO
 *   返回封装
 */
@Data
@AllArgsConstructor
public class Wrapper<T> {
    private int code;
    private String msg;
    private T data;

}
