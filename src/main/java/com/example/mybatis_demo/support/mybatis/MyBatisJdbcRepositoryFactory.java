/*
 * Copyright 2017-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mybatis_demo.support.mybatis;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;
import org.springframework.data.jdbc.repository.support.JdbcRepositoryFactory;
import org.springframework.data.mapping.callback.EntityCallbacks;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.lang.Nullable;

import java.util.Optional;

/**
 * Creates repository implementation based on JDBC.
 *
 * @author Jens Schauder
 * @author Greg Turnquist
 * @author Christoph Strobl
 * @author Mark Paluch
 * @author Hebert Coelho
 * @author Diego Krupitza
 * @author Christopher Klein
 */
public class MyBatisJdbcRepositoryFactory extends JdbcRepositoryFactory {

    private final RelationalMappingContext context;
    private final JdbcConverter converter;
    private final ApplicationEventPublisher publisher;
    private final NamedParameterJdbcOperations operations;
    private final Dialect dialect;
    @Nullable
    private BeanFactory beanFactory;

    private QueryMappingConfiguration queryMappingConfiguration = QueryMappingConfiguration.EMPTY;
    private EntityCallbacks entityCallbacks;

    /**
     * Creates a new {@link MyBatisJdbcRepositoryFactory} for the given {@link DataAccessStrategy},
     * {@link RelationalMappingContext} and {@link ApplicationEventPublisher}.
     *
     * @param dataAccessStrategy must not be {@literal null}.
     * @param context            must not be {@literal null}.
     * @param converter          must not be {@literal null}.
     * @param dialect            must not be {@literal null}.
     * @param publisher          must not be {@literal null}.
     * @param operations         must not be {@literal null}.
     */
    public MyBatisJdbcRepositoryFactory(DataAccessStrategy dataAccessStrategy, RelationalMappingContext context,
                                        JdbcConverter converter, Dialect dialect, ApplicationEventPublisher publisher,
                                        NamedParameterJdbcOperations operations) {

        super(dataAccessStrategy,context,converter, dialect, publisher,operations);
        this.publisher = publisher;
        this.context = context;
        this.converter = converter;
        this.dialect = dialect;
        this.operations = operations;
    }

    @Override
    protected Optional<QueryLookupStrategy> getQueryLookupStrategy(@Nullable QueryLookupStrategy.Key key,
                                                                   QueryMethodEvaluationContextProvider evaluationContextProvider) {

        return Optional.of(MyBatisJdbcQueryLookupStrategy.create(key, publisher, entityCallbacks, context, converter, dialect,
                queryMappingConfiguration, operations, beanFactory, evaluationContextProvider));
    }

    /**
     * @param entityCallbacks
     * @since 1.1
     */
    public void setEntityCallbacks(EntityCallbacks entityCallbacks) {
        this.entityCallbacks = entityCallbacks;
    }

    /**
     * @param beanFactory the {@link BeanFactory} used for looking up {@link org.springframework.jdbc.core.RowMapper} and
     *          {@link org.springframework.jdbc.core.ResultSetExtractor} beans.
     */
    public void setBeanFactory(@Nullable BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
