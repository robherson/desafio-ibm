package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Command;

public interface CommandHandler<T extends Command> {
    void handle(T command);
    Class<T> supportedCommandType(); 
}