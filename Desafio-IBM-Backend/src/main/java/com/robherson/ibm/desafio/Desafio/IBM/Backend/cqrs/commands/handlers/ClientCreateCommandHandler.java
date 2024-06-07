package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.handlers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.ClientCreateCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.CommandHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.ClientRepository;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.services.SequenceGeneratorService;

@Component
public class ClientCreateCommandHandler implements CommandHandler<ClientCreateCommand> {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public void handle(ClientCreateCommand command) {
        
        Client client = Client.builder()
            .id(UUID.randomUUID().toString())
            .name(command.getName())
            .email(command.getEmail())
            .age(command.getAge())
            .accountNumber(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME))
            .build();

            clientRepository.save(client);
    }
    
    @Override
    public Class<ClientCreateCommand> supportedCommandType() {
        return ClientCreateCommand.class;
    }
}
