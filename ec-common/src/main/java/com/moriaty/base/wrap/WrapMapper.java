package com.moriaty.base.wrap;


import com.moriaty.base.constant.CustomConstant;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/11/15 10:18
 * @Description TODO
 * 返回方法封装
 */
public class WrapMapper {
    private WrapMapper() {

    }

    /**
     * 成功封装
     *
     * @param msg
     * @param data
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> ok(String msg, E data) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_SUCCESS, msg, data);
    }

    /**
     * 成功封装
     *
     * @param msg
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> ok(String msg) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_SUCCESS, msg, null);
    }


    /**
     * 错误封装
     *
     * @param msg
     * @return Wrapper<E>
     */
    public static <E> Wrapper<E> error(String msg) {
        return new Wrapper<E>(CustomConstant.Wrap.CODE_ERROR, msg, null);
    }
}
