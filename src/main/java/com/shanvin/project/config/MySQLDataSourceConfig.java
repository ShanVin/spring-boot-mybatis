package com.shanvin.project.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.shanvin.project.interceptor.SQLExecuteTimeInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@MapperScan(basePackages = "com.shanvin.project.mapper.mysql", sqlSessionTemplateRef = "mySQLSqlSessionTemplate")
@Configuration
public class MySQLDataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource.druid.mysql")
    @Bean(name = "mySQLDataSource")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mySQLDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier(value = "mySQLDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mySQLSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "mySQLDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mysql/*.xml"));
        bean.setPlugins(new Interceptor[]{new SQLExecuteTimeInterceptor()});
        return bean.getObject();
    }

    @Bean(name = "mySQLSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(value = "mySQLSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
