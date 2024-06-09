package com.robherson.ibm.desafio.Desafio.IBM.Backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Mediator;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.OperationCreateCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.GetAllOperationsByClientQuery;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.ClientNotFoundException;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.InsufficientFundsException;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Operation;



@RestController
@RequestMapping("/api/operations")
public class OperationController {


    @Autowired
    private Mediator mediator;

    @GetMapping("extract/{clientId}")
    public ResponseEntity<Object> findAllOperationsByClient(@PathVariable String clientId) {
        try {
            GetAllOperationsByClientQuery query = new GetAllOperationsByClientQuery();
            query.setClientId(clientId);
            List<Operation> operations = new ArrayList<>();
            operations = mediator.dispatch(query);
            return ResponseEntity.ok(operations);           
        } catch (Exception e) {
            if (e.getClass().equals(ClientNotFoundException.class)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Cliente não encontrado.\"}");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Ocorreu um erro ao buscar as operações.\"}");
        }
    }

    @PostMapping
    public ResponseEntity<Object> createOperation(@RequestBody OperationCreateCommand command) {
        try {
            mediator.dispatch(command);
            return ResponseEntity.ok("{\"message\": \"Operação realizada com sucesso.\"}");
        } catch (Exception e) {
            if (e.getClass().equals(ClientNotFoundException.class)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Cliente não encontrado.\"}");
            }
            if (e.getClass().equals(InsufficientFundsException.class)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Saldo insuficiente.\"}");
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Ocorreu um erro ao buscar o cliente.\"}"); 
        }
    }
    
    
}
