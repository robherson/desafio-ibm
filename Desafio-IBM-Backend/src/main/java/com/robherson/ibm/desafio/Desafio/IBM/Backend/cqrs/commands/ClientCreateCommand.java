package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands;


import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Command;

import lombok.Data;

@Data
public class ClientCreateCommand implements Command {

    private String name;
    private Integer age;
    private String email;
    private Integer accountNumber;
    
}
