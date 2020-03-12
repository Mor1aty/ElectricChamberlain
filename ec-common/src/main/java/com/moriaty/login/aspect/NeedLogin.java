package com.moriaty.login.aspect;

import java.lang.annotation.*;

/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2020/3/12 22:12
 * @Description TODO
 *   NeedLogin 注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLogin {
    String value() default "token";
}
