package com.moriaty.ctrl;

import com.moriaty.base.wrap.WrapMapper;
import com.moriaty.base.wrap.Wrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/13 22:20
 * @Description TODO
 * 全局异常处理 ctrl
 */
@ControllerAdvice
public class GlobalExceptionCtrl {

    // 请求方法异常处理
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Wrapper<String> exceptionHandler() {
        return WrapMapper.error("请求方法错误");
    }

    // 全局异常处理
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Wrapper<String> globalExceptionHandler(Exception e) {
        e.printStackTrace();
        return WrapMapper.error("服务器异常");
    }
}
