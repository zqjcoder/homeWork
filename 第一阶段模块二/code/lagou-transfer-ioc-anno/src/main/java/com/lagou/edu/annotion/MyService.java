package com.lagou.edu.annotion;

import java.lang.annotation.*;

/**
 * 自定义注解，该注解作用为：向IOC容器注入bean
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyService {
}
