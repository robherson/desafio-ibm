package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Command;

import lombok.Data;

@Data
public class ClientDeleteCommand implements Command {

    private String id;
    
}
