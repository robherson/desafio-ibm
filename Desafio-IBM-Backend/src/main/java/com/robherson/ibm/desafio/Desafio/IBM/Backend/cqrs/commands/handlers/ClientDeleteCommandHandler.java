package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.ClientDeleteCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.CommandHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.ClientNotFoundException;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.ClientRepository;

@Component
public class ClientDeleteCommandHandler implements CommandHandler<ClientDeleteCommand>{
    
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void handle(ClientDeleteCommand command) {
        try {
            Client client = clientRepository.findById(command.getId())
                .orElseThrow(() -> new ClientNotFoundException("Cliente com o ID " + command.getId() + " não encontrado!"));
            
            clientRepository.delete(client);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Class<ClientDeleteCommand> supportedCommandType() {
        return ClientDeleteCommand.class;
    }
    
}
