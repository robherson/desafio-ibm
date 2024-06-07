package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.handlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.GetClientByIdQuery;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.QueryHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.ClientNotFoundException;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.ClientRepository;

@Component
public class GetClientByIdQueryHandler implements QueryHandler<GetClientByIdQuery, Client> {

    @Autowired
    private ClientRepository clientRepository;
    
    @Override
    public Client handle(GetClientByIdQuery query)  {
        return clientRepository.findById(query.getId())
            .orElseThrow(() -> new ClientNotFoundException("Cliente com o ID " + query.getId() + " não encontrado!"));
    }

    @Override
    public Class<GetClientByIdQuery> supportedQueryType() {
        return GetClientByIdQuery.class;
    } 
    
}
