package com.robherson.ibm.desafio.Desafio.IBM.Backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Mediator;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.ClientCreateCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.ClientDeleteCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.commands.ClientUpdateCommand;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.GetAllClientsQuery;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries.GetClientByIdQuery;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.exceptions.ClientNotFoundException;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {


    @Autowired
    private Mediator mediator;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable String id) {
        try {
            GetClientByIdQuery query = new GetClientByIdQuery();
            query.setId(id);
            
            Client client = mediator.dispatch(query);
            return ResponseEntity.ok(client);
            
        } catch (Exception ex) {
            if (ex.getClass().equals(ClientNotFoundException.class))  
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao buscar o cliente.");
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllClients() {
        try {
            GetAllClientsQuery query = new GetAllClientsQuery();
            List<Client> clients = new ArrayList<>();
            clients = mediator.dispatch(query);
            return ResponseEntity.ok(clients);
           
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao buscar o cliente.");
        }
    }

    
    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody ClientCreateCommand client) {
        try {
            mediator.dispatch(client);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Cliente cadastrado com sucesso\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao cadastrar o cliente.");
        }
    }

    @PutMapping
    public  ResponseEntity<Object> updateClient(@RequestBody ClientUpdateCommand client) {
        
        try {
            mediator.dispatch(client);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso.");
        } catch (Exception e) {

            if (e.getClass() == ClientNotFoundException.class) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado na base.");
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao atualizar o cliente.");

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable String id){

        try {
            ClientDeleteCommand command = new ClientDeleteCommand();
            command.setId(id);            
            mediator.dispatch(command);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso.");
        } catch (Exception e) {
            if (e.getClass() == ClientNotFoundException.class) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado na base.");
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao deletar o cliente.");
        }
        

    }
    
}