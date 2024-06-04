package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands;


import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Command;

import lombok.Data;

@Data
public class ClienteCreateCommand implements Command {

    private String nome;
    private Integer idade;
    private String email;
    private Integer numeroConta;
    
}
