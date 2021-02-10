package com.shanvin.project.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
public class SQLExecuteTimeInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger("ApplicationLogger");

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        Object object = invocation.proceed();
        logger.info("SQL execute time: {}", System.currentTimeMillis() - currentTimeMillis);
        return object;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {}

}
