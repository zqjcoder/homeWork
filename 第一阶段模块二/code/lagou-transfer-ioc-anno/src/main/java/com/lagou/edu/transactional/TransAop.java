package com.lagou.edu.transactional;

import com.lagou.edu.annotion.MyAutoWired;
import com.lagou.edu.annotion.MyService;
import com.lagou.edu.annotion.MyTransactional;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;

/**
 * 使用自定义注解完成实例初始化
 */
@MyService
public class TransAop {
    @MyAutoWired
    TransactionUtils transactionUtils;

    @AfterThrowing("execution(* com.lagou.edu.service.impl.*(..))")
    public void afterThrowing(){
        //将当前事务进行回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }


    @Around("execution(* com.lagou.edu.service.impl.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //1、判断当前代理对象的方法时候开启了MyTransactional注解
        MyTransactional myTransactional = getMyTransactional(proceedingJoinPoint);

        //2、开启事务
        transactionUtils.begin();

        //3、执行目标方法
        proceedingJoinPoint.proceed();

        //4、提交事务
        transactionUtils.commit();
    }

    /*
     * 判断方法是否开启MyTransactional注解
     */
    public MyTransactional getMyTransactional(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        //获取代理对象的方法名称
        String methodName = proceedingJoinPoint.getSignature().getName();
        //获取目标对象类型
        Class<?> classTarget = proceedingJoinPoint.getTarget().getClass();
        //获取参数类型
        Class[] parameterTypes = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes();
        //获取目标对象方法
        Method method = classTarget.getMethod(methodName, parameterTypes);
        MyTransactional declaredAnnotation = method.getDeclaredAnnotation(MyTransactional.class);
        return declaredAnnotation;
    }
}