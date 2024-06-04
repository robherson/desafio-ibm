package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries;

public interface QueryHandler<TQuery, TResult> {
    TResult handle(TQuery query);
}