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
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.ClientCreateCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.GetAllClientsQuery;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.GetClientByIdQuery;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/clientes")
public class ClientController {


    @Autowired
    private Mediator mediator;

    @GetMapping("/{id}")
    public ResponseEntity<Object> obterClientePorId(@PathVariable String id) {
        try {
            GetClientByIdQuery query = new GetClientByIdQuery();
            query.setId(id);
            
            Client cliente = mediator.dispatch(query);
            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao buscar o cliente.");
        }
    }

    @GetMapping
    public ResponseEntity<Object> obterTodosClientes() {
        try {
            GetAllClientsQuery query = new GetAllClientsQuery();
            List<Client> clientes = new ArrayList<>();
            clientes = mediator.dispatch(query);
            return ResponseEntity.ok(clientes);
           
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao buscar o cliente.");
        }
    }

    
    @PostMapping
    public ResponseEntity<Object> cadastrarCliente(@RequestBody ClientCreateCommand cliente) {
        try {
            mediator.dispatch(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao cadastrar o cliente.");
        }
    }

    // @PutMapping("/{id}")
    // public  ResponseEntity<Object> atualizarCliente(@RequestBody ClientCreateCommand cliente) {
    //     //TODO: process PUT request
        
    //     return entity;
    // }
    
}