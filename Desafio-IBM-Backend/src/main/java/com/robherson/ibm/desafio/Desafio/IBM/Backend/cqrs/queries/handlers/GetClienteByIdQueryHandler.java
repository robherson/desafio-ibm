package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.handlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.GetClienteByIdQuery;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.QueryHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Cliente;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.ClienteRepository;

@Component
public class GetClienteByIdQueryHandler implements QueryHandler<GetClienteByIdQuery, Cliente> {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public Cliente handle(GetClienteByIdQuery query)  {
        return clienteRepository.findById(query.getId())
            .orElseThrow();
    }

    @Override
    public Class<GetClienteByIdQuery> supportedQueryType() {
        return GetClienteByIdQuery.class;
    } 
    
}
