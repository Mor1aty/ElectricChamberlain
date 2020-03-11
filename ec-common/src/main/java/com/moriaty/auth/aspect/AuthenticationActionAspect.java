package com.moriaty.auth.aspect;

import com.moriaty.auth.bean.Method;
import com.moriaty.base.constant.CustomConstant;
import com.moriaty.base.utils.ValueUtils;
import com.moriaty.base.utils.WrapUtils;
import com.moriaty.base.wrap.WrapMapper;
import com.moriaty.base.wrap.WrapParams;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/11/15 11:16
 * @Description TODO
 * Authentication 注解切面处理
 */
@Aspect
@Component
public class AuthenticationActionAspect {

    @Pointcut("@annotation(com.moriaty.auth.aspect.Authentication)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object actionAround(ProceedingJoinPoint pjp) throws Throwable {
        // 获取当前正在执行的类
        Class<?> classTarget = pjp.getTarget().getClass();
        // 获取当前正在执行的方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取注解参数
        Param[] authParams = classTarget.getMethod(signature.getName(), signature.getParameterTypes()).getAnnotation(Authentication.class).value();
        // 获取 request response
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        // 获取传入参数
        WrapParams wrapParams = WrapUtils.paramToWrapParams(request);
        // 如果没有传入参数
        if (ValueUtils.valEmpty(wrapParams)) {
            return WrapMapper.error(CustomConstant.Wrap.MSG_PARAM_EMPTY);
        }
        // 参数校验
        for (Param authParam : authParams) {
            // 参数 key
            String paramKey = authParam.value();
            // 参数 value
            Object paramValue = wrapParams.get(paramKey);
            if (ValueUtils.valEmpty(paramValue)) {
                return WrapMapper.error(paramKey + CustomConstant.Wrap.MSG_PARAM_NULL);
            }
            String res = handleParam(paramValue, authParam.method());
            if (ValueUtils.valNotEmpty(res)) {
                return WrapMapper.error(paramKey + res);
            }
        }
        return pjp.proceed(new Object[]{wrapParams});
    }

    // 处理参数
    private String handleParam(Object value, Method[] methods) {
        for (Method method : methods) {
            switch (method) {
                case EMAIL:
                    if (!String.valueOf(value).matches(CustomConstant.Regular.MAIL)) {
                        return CustomConstant.Wrap.MSG_PARAM_EMAIL;
                    }
                    break;
                case NUMBER:
                    if (!String.valueOf(value).matches(CustomConstant.Regular.NUMBER)) {
                        return CustomConstant.Wrap.MSG_PARAM_NUMBER;
                    }
                    break;
                case PHONE:
                    if (!String.valueOf(value).matches(CustomConstant.Regular.PHONE)) {
                        return CustomConstant.Wrap.MSG_PARAM_PHONE;
                    }
                    break;
            }
        }
        return null;
    }

}
