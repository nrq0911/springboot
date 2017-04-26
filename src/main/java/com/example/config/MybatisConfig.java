package com.example.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by nrq on 2017/1/4.
 */
@Configuration
@MapperScan("com.example.mapper")  //扫描mapper.java
public class MybatisConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory()throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        org.apache.ibatis.session.Configuration cfg = new org.apache.ibatis.session.Configuration();
        cfg.setCacheEnabled(true);   //使全局的映射器启用或禁用缓存
        cfg.setLazyLoadingEnabled(true); //全局启用或禁用延迟加载。当禁用时，所有关联对象都会即时加载
        cfg.setMapUnderscoreToCamelCase(true);  //使用驼峰命名法转换字段
        cfg.setCallSettersOnNulls(true); //当列的数据为空值时，mybatis在返回的map中并不会存在对应的key,此配置可避免没有空值key
        sqlSessionFactoryBean.setConfiguration(cfg);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});

        //设置实体类的别名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.domain");
        //注册handler
        sqlSessionFactoryBean.setTypeHandlersPackage("com.example.mapper.handler");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //加载mapper.xml文件
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    //配置事务
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

}
