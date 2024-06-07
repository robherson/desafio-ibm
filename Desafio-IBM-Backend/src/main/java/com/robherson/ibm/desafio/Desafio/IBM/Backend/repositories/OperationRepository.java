package com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Operation;

public interface OperationRepository extends MongoRepository<Operation, String> {

    List<Operation> findByClient(Client client);
    
}
