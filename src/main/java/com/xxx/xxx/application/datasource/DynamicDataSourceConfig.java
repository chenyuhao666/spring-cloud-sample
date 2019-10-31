package com.xxx.xxx.application.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置，如果有更多数据源也是在该出配置，下面为讲解，实际使用请调整
 *
 * @author yan.li
 */
@Configuration
public class DynamicDataSourceConfig {

    /**
     * 环境信息
     */
    @Autowired
    Environment environment;

    /**
     * Bean：后面指定名称为bean的id值，若不指定，默认为方法名称,建议指定名称
     * ConfigurationProperties：指定获取Properties参数的前缀
     *
     * @return
     */
    @Bean("masterDataSource")
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Bean：后面指定名称为bean的id值，若不指定，默认为方法名称,建议指定名称
     * ConfigurationProperties：指定获取Properties参数的前缀
     *
     * @return
     */
    @Bean("slaveDataSource")
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 通过构造函数的方法设定参数数据，参数数据通过上面两个定义的Bean进行设定
     * <p>
     * Primary :标签代表其他地方如果通过Autowired注入DynamicDataSource的话，直接使用该配置，比如该类中有三个DataSource实现
     * Bean：后面指定名称为bean的id值，若不指定，默认为方法名称,建议指定名称
     * Qualifier：指定Autowired对应的bean的id，如果不指定，则默认参数名称必须等于对应bean的id
     *
     * @param masterDataSource
     * @param slaveDataSource
     * @return
     */
    @Primary
    @Bean("dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.MASTER, masterDataSource);
        targetDataSources.put(DataSourceNames.SLAVE, slaveDataSource);

        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    /**
     * 根据动态数据源生成SqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("sessionFactory")
    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sessionFactoryBean.setMapperLocations(resolver.getResources(environment.getProperty("mybatis.mapper-locations")));

        return sessionFactoryBean.getObject();
    }
}
