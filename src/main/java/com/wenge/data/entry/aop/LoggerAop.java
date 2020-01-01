package com.wenge.data.entry.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 孟胜
 * @Create: 2019-06-03
 * @Description: 切入所有的controller，打印每次请求的入参、出参和耗时
 */
@Slf4j
@Aspect
@Configuration
public class LoggerAop {

    /**
     * 切入所有的controller
     */
    private static final String POINT = "execution (* com.wenge.data.entry.controller.*.*(..))";

    /**
     * 统计每次请求的耗时
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINT)
    public Object consAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringType().getSimpleName() + "." + signature.getName();
        this.printBeginLog(methodName, args);
        Object result = joinPoint.proceed(args);
        this.printResultLog(methodName, result, startTime);
        return result;
    }

    /**
     * 打印每次方法的入参及方法名
     * @param methodName
     * @param args
     */
    private void printBeginLog(String methodName, Object[] args) {
        log.info("{} begin param {}", methodName, JSONObject.toJSONString(args, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.IgnoreNonFieldGetter));
    }

    /**
     * 打印每次请求的出参和耗时
     * @param methodName
     * @param result
     * @param startTime
     */
    private void printResultLog(String methodName, Object result, long startTime) {
        String resStr = null;
        if (null != result) {
            resStr = JSONObject.toJSONString(result, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.IgnoreNonFieldGetter);
            if (resStr.length() > 1000) {
                resStr = resStr.substring(0, 1000) + "......";
            }
        }
        log.info("{} success [cost:{}]ms result:{}", methodName, (System.currentTimeMillis() - startTime), resStr);
    }
}
