package com.moriaty.login.aspect;

import com.moriaty.base.constant.CustomConstant;
import com.moriaty.base.utils.ValueUtils;
import com.moriaty.base.wrap.WrapMapper;
import com.moriaty.login.storage.Token;
import com.moriaty.login.utils.TokenUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/12 22:14
 * @Description TODO
 * NeedLogin 注解切面处理
 */
@Aspect
@Component
public class NeedLoginActionAspect {

    @Pointcut("@annotation(com.moriaty.login.aspect.NeedLogin)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object actionAround(ProceedingJoinPoint pjp) throws Throwable {
        // 获取当前正在执行的类
        Class<?> classTarget = pjp.getTarget().getClass();
        // 获取当前正在执行的方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取 request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取注解参数
        String value = classTarget.getMethod(signature.getName(), signature.getParameterTypes()).getAnnotation(NeedLogin.class).value();

        // 调用方法验证 request 的 Authorization
        Token token = TokenUtil.checkToken(request.getHeader("authorization"));
        if (ValueUtils.valEmpty(token)) {
            return WrapMapper.error(CustomConstant.Wrap.MSG_TOKEN_FAILED);
        }

        request.setAttribute(value, token);
        return pjp.proceed();
    }
}
