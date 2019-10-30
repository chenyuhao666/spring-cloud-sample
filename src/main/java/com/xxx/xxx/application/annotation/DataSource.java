package com.xxx.xxx.application.annotation;

import java.lang.annotation.*;

/**
 * 数据源注解
 *
 * @author yan.li
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    /**
     * 指定数据源名称
     *
     * @return
     */
    String name() default "";
}
