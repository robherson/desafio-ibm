package com.robherson.ibm.desafio.Desafio.IBM.Backend.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "operations")
public class Operation {

    @Id
    private String id;

    private OperationType operationType;
    private double value;
    private LocalDateTime date;

    @DBRef
    private Client client;
    
}
