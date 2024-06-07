package com.robherson.ibm.desafio.Desafio.IBM.Backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "clients")
public class Client {

    @Id
    private String id;

    private String name;
    private Integer age;
    private String email;
    private long accountNumber;

    public static final String SEQUENCE_NAME = "clients_sequence";
    
}
