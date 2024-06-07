package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.ClientUpdateCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.CommandHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.ClientNotFoundException;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.ClientRepository;

@Component
public class ClientUpdateCommandHandler implements CommandHandler<ClientUpdateCommand> {
    
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void handle(ClientUpdateCommand command) {
        try {
            Client client = clientRepository.findById(command.getId())
                .orElseThrow(() -> new ClientNotFoundException("Cliente com o ID " + command.getId() + " não encontrado!"));

            client.setName(command.getName());
            client.setAge(command.getAge());
            client.setEmail(command.getEmail());

            clientRepository.save(client);
        } catch (Exception e) {
            throw new ClientNotFoundException("Cliente com o ID " + command.getId() + " não encontrado!");
        }
    }

    @Override
    public Class<ClientUpdateCommand> supportedCommandType() {
        return ClientUpdateCommand.class;
    }
    
}
