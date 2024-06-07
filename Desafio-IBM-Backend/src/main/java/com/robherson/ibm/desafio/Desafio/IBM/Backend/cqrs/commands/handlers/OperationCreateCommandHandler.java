package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.handlers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.CommandHandler;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.OperationCreateCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.ClientNotFoundException;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.InsufficientFundsException;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Operation;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.OperationType;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.ClientRepository;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.repositories.OperationRepository;

@Component
public class OperationCreateCommandHandler implements CommandHandler<OperationCreateCommand> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public void handle(OperationCreateCommand command) {
        try {
            Client client = clientRepository.findById(command.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Cliente com o ID " + command.getClientId() + " não encontrado!"));
            
            if(command.getOperationType().equals(OperationType.WITHDRAWAL)){
                if (client.getBalance() < command.getValue()){
                    throw new InsufficientFundsException("Saldo Insuficiente.");
                } else {
                    client.setBalance(client.getBalance() - command.getValue());
                }
            } else {
                client.setBalance(client.getBalance() + command.getValue());
            }

            clientRepository.save(client);

            Operation operation = Operation.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDateTime.now())
                .value(command.getValue())
                .operationType(command.getOperationType())
                .client(client)
                .build();
            
            operationRepository.save(operation);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Class<OperationCreateCommand> supportedCommandType() {
        return OperationCreateCommand.class;
    }
    
}
