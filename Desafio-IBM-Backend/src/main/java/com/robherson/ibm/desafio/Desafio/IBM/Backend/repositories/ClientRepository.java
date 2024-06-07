package com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;

public interface ClientRepository extends MongoRepository<Client, String> {
    
}
