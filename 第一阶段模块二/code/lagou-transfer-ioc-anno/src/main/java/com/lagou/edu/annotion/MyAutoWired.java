package com.lagou.edu.annotion;

import java.lang.annotation.*;

/**
 * 自定义注解，该注解作用为：向IOC容器注入bean，引用元注解及运行时
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAutoWired {
}
