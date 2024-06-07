package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.GetAllClientesQuery;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.QueryHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Cliente;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.ClienteRepository;

@Component
public class GetAllClientesQueryHandler implements QueryHandler<GetAllClientesQuery, List<Cliente>> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> handle(GetAllClientesQuery query) {
        return clienteRepository.findAll();
    }

    @Override
    public Class<GetAllClientesQuery> supportedQueryType() {
       return GetAllClientesQuery.class;
    }
    
}
