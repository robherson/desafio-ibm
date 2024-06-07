package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Command;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.OperationType;

import lombok.Data;

@Data
public class OperationCreateCommand implements Command {

    private OperationType operationType;
    private double value;
    private String clientId;
    
}
