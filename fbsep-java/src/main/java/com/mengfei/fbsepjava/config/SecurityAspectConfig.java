package com.mengfei.fbsepjava.config;

import com.mengfei.fbsepjava.annotation.UserTokenCheck;
import com.mengfei.fbsepjava.exception.TokenException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 用于用户Token安全检查的切面配置类
 */
@Component
@Aspect
public class SecurityAspectConfig {
    //伪Token，暂存于内存，通常的解决方案是在用户登录时将Token信息存储在Redis中
    private static String USER_TOKEN = "123456789";

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        //获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //从切入点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        //若目标方法需要安全性检查,则进行Token验证，如果不进行此判断可以对所有HTTP请求进行安全性检查
        if (method.isAnnotationPresent(UserTokenCheck.class)) {
            // 从 request header 中获取当前 token
            String token = request.getHeader("userToken");
            // 检查 token 有效性
            if (!USER_TOKEN.equals(token)) {
                String message = String.format("用户Token验证不通过", token);
                throw new TokenException(message);
            }
        }
        // 调用目标方法
        return pjp.proceed();
    }
}
