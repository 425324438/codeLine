package com.code.line.domain.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author: syl
 * @Date: 2022/9/22 00:03
 * @Description: 日志切面，打印server层入参，出参，异常堆栈
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.code.line.domain.controller.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint jp){

        //获取HttpServletRequest对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("==========请求信息==========");
        log.info("请求链接 : {}",request.getRequestURL().toString());
        log.info("Http Method : {}",request.getMethod());
        log.info("Class Method : {}.{}",jp.getSignature().getDeclaringTypeName(),jp.getSignature().getName());
        log.info("Ip : {}",request.getRemoteAddr());
        log.info("Args : {}", Arrays.asList(jp.getArgs()));
    }

    @Around("pointcut()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info("执行时间 : {} ms", System.currentTimeMillis() - startTime);
        log.info("返回参数 : {}", result);
        return result;
    }

    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void AfterThrowing(Exception ex){
        log.error("ex={}",ex);
    }
}
