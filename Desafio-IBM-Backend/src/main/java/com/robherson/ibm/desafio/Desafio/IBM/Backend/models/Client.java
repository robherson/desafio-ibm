package com.robherson.ibm.desafio.Desafio.IBM.Backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "clientes")
public class Client {

    @Id
    private String id;

    private String nome;
    private Integer idade;
    private String email;
    private long numeroConta;

    public static final String SEQUENCE_NAME = "clientes_sequence";
    
}
