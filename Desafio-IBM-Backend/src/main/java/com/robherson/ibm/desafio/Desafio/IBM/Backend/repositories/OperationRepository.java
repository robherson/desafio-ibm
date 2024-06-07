package com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Operation;

public interface OperationRepository extends MongoRepository<Operation, String> {
    
}
