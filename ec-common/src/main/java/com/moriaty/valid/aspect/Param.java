package com.moriaty.valid.aspect;

import com.moriaty.valid.bean.Method;

import java.lang.annotation.*;

/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2019/11/15 11:15
 * @Description TODO
 *   Param 注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param{
    String value();
    Method[] method() default Method.NOTNULL;
}
