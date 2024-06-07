package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.GetAllOperationsByClientQuery;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.QueryHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.ClientNotFoundException;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Operation;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.ClientRepository;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.OperationRepository;

@Component
public class GetAllOperationsByClientQueryHandler implements QueryHandler<GetAllOperationsByClientQuery, List<Operation>> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OperationRepository operationRepository;


    @Override
    public List<Operation> handle(GetAllOperationsByClientQuery query) {

        Client client = clientRepository.findById(query.getClientId())
            .orElseThrow(() -> new ClientNotFoundException("Cliente com o ID " + query.getClientId() + " não encontrado!"));

        return operationRepository.findByClient(client);
    }

    @Override
    public Class<GetAllOperationsByClientQuery> supportedQueryType() {
        return GetAllOperationsByClientQuery.class;
    }
    
}
