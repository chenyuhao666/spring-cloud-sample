package com.xxx.xxx.application.annotation;

import com.xxx.xxx.application.datasource.DataSourceNames;
import com.xxx.xxx.application.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 动态数据源切面实现
 *
 * @author yan.li
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    /**
     * 注解执行切面
     */
    @Pointcut("@annotation(com.xxx.xxx.application.annotation.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 获取切面对应的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        try {
            DataSource dataSource = method.getAnnotation(DataSource.class);
            if (dataSource == null) {
                DynamicDataSource.setDataSourceName(DataSourceNames.MASTER);
                log.debug("Default set datasource is {}", DataSourceNames.MASTER);
            } else {
                DynamicDataSource.setDataSourceName(dataSource.name());
                log.debug("Set datasource is {}", dataSource.name());
            }

            // 执行处理
            return point.proceed();
        } finally {
            // 清理对应线程数据源名称
            DynamicDataSource.clearDataSourceName();
            log.debug("Clean datasource !");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
