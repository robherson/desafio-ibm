package com.robherson.ibm.desafio.Desafio.IBM.Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Mediator;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.ClienteCreateCommand;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    @Autowired
    private Mediator mediator;

    
    @PostMapping
    public ResponseEntity<Object> createCliente(@RequestBody ClienteCreateCommand cliente) {
        try {
            mediator.dispatch(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao cadastrar o cliente.");
        }
    }
   
}