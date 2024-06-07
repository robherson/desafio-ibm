package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Query;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Client;

import lombok.Data;

@Data
public class GetClientByIdQuery implements Query<Client> {
    private String id;
}
