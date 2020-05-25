package com.lagou.edu.annotion;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义注解，该注解作用为：向IOC容器注入bean
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyService {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}
