package com.robherson.ibm.desafio.Desafio.IBM.Backend.models;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;
    
}
