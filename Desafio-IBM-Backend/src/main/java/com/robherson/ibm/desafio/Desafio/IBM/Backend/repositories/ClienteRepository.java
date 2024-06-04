package com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    
}
