package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.GetAllClientsQuery;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.QueryHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.ClientRepository;

@Component
public class GetAllClientsQueryHandler implements QueryHandler<GetAllClientsQuery, List<Client>> {

    @Autowired
    private ClientRepository clienteRepository;

    @Override
    public List<Client> handle(GetAllClientsQuery query) {
        return clienteRepository.findAll();
    }

    @Override
    public Class<GetAllClientsQuery> supportedQueryType() {
       return GetAllClientsQuery.class;
    }
    
}
