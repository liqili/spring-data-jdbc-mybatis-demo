package com.example.mybatis_demo.support.mybatis.query;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyBatisQueryMethod extends QueryMethod {

    private final Method method;
    private final Object mapper;

    /**
     * Creates a new {@link QueryMethod} from the given parameters. Looks up the correct query to use for following
     * invocations of the method given.
     *
     * @param method   must not be {@literal null}.
     * @param metadata must not be {@literal null}.
     * @param factory  must not be {@literal null}.
     */
    public MyBatisQueryMethod(Method method, RepositoryMetadata metadata, ProjectionFactory factory, Object mapper) {
        super(method, metadata, factory);
        this.method = method;
        this.mapper = mapper;
    }



    Object invoke(Object[] args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(mapper, args);
    }
}
