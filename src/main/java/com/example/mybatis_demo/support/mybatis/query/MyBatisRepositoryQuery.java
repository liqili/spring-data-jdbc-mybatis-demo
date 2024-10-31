package com.example.mybatis_demo.support.mybatis.query;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.repository.query.RepositoryQuery;

import java.lang.reflect.InvocationTargetException;

public class MyBatisRepositoryQuery implements RepositoryQuery {
    
    private final ApplicationEventPublisher publisher;
    private final RelationalMappingContext context;
    private final MyBatisQueryMethod mybatisQueryMethod;

    public MyBatisRepositoryQuery(ApplicationEventPublisher publisher, RelationalMappingContext context, MyBatisQueryMethod mybatisQueryMethod) {
        this.publisher = publisher;
        this.context = context;
        this.mybatisQueryMethod = mybatisQueryMethod;
    }

    @Override
    public Object execute(Object[] parameters) {
        try {
            return mybatisQueryMethod.invoke(parameters);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getTargetException());
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Invoke from mybatis mapper failed", e);
        }
    }

    @Override
    public MyBatisQueryMethod getQueryMethod() {
        return mybatisQueryMethod;
    }

}
