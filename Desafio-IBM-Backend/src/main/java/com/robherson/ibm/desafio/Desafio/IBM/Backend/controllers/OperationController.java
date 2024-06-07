package com.robherson.ibm.desafio.Desafio.IBM.Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Mediator;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.OperationCreateCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.ClientNotFoundException;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.InsufficientFundsException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/operations")
public class OperationController {


    @Autowired
    private Mediator mediator;

    @PostMapping
    public ResponseEntity<Object> createOperation(@RequestBody OperationCreateCommand command) {
        try {
            mediator.dispatch(command);
            return ResponseEntity.ok("Operação realizada com sucesso");
        } catch (Exception e) {
            if (e.getClass().equals(ClientNotFoundException.class)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
            }
            if (e.getClass().equals(InsufficientFundsException.class)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente.");
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao buscar o cliente."); 
        }
    }
    
    
}
