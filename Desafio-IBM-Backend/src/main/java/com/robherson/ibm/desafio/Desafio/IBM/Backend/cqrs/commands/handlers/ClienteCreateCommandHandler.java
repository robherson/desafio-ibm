package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.handlers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.ClienteCreateCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.CommandHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Cliente;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.ClienteRepository;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.services.SequenceGeneratorService;

@Component
public class ClienteCreateCommandHandler implements CommandHandler<ClienteCreateCommand> {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public void handle(ClienteCreateCommand command) {
        
        Cliente cliente = Cliente.builder()
            .id(UUID.randomUUID().toString())
            .nome(command.getNome())
            .email(command.getEmail())
            .idade(command.getIdade())
            .numeroConta(sequenceGeneratorService.generateSequence(Cliente.SEQUENCE_NAME))
            .build();

        clienteRepository.save(cliente);
    }
    
    @Override
    public Class<ClienteCreateCommand> supportedCommandType() {
        return ClienteCreateCommand.class;
    }
}
