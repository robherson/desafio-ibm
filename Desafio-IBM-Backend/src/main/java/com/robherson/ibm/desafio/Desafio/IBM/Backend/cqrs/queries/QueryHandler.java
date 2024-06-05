package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Query;


public interface QueryHandler<TQuery extends Query<TResult>, TResult> {
    TResult handle(TQuery query);
    Class<TQuery> supportedQueryType();
}