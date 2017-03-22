package com.sensir.studio.spring.aop;

import com.sensir.studio.data.source.DataSource;
import com.sensir.studio.data.source.DataSourceHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Leon on 2017-3-9.
 */
@Aspect
@Component
@Order(1)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ServiceAspect {
    private final static Log log = LogFactory.getLog(ServiceAspect.class);

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.sensir.service..*(..))")
    public void aspect() {
    }

    /*
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
    @Before("aspect()")
    public void before(JoinPoint point){
        if(log.isInfoEnabled()){
            log.info("before " + point);
        }

        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource dataSource = null;
        //从类初始化
        dataSource = this.getDataSource(target, method);
        //从接口初始化
        if (dataSource == null) {
            for (Class<?> clazz : target.getInterfaces()) {
                dataSource = getDataSource(clazz, method);
                if (dataSource != null) {
                    break;//从某个接口中一旦发现注解，不再循环
                }
            }
        }

        if (dataSource != null && !StringUtils.isEmpty(dataSource.value())) {
            DataSourceHolder.setDataSource(dataSource.value());
        }
    }

    //配置后置通知,使用在方法aspect()上注册的切入点
    @After("aspect()")
    public void after(JoinPoint joinPoint){
        if(log.isInfoEnabled()){
            log.info("after " + joinPoint);
        }
        DataSourceHolder.removeDataSource();
    }

    //配置环绕通知,使用在方法aspect()上注册的切入点
    @Around("aspect()")
    public void around(JoinPoint joinPoint){
        long start = System.currentTimeMillis();
        try {
            ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            if(log.isInfoEnabled()){
                log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            }
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            if(log.isInfoEnabled()){
                log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            }
        }
    }

    //配置后置返回通知,使用在方法aspect()上注册的切入点
    @AfterReturning("aspect()")
    public void afterReturn(JoinPoint joinPoint){
        if(log.isInfoEnabled()){
            log.info("afterReturn " + joinPoint);
        }
    }

    //配置抛出异常后通知,使用在方法aspect()上注册的切入点
    @AfterThrowing(pointcut="aspect()", throwing="ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex){
        if(log.isInfoEnabled()){
            log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
        }
    }

    /**
     * 获取方法或类的注解对象DataSource
     *
     * @param target 类class
     * @param method 方法
     * @return DataSource
     */
    public DataSource getDataSource(Class<?> target, Method method) {
        try {
            //1.优先方法注解
            Class<?>[] types = method.getParameterTypes();
            Method m = target.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                return m.getAnnotation(DataSource.class);
            }
            //2.其次类注解
            if (target.isAnnotationPresent(DataSource.class)) {
                return target.getAnnotation(DataSource.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
