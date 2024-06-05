package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Query;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Cliente;

import lombok.Data;

@Data
public class GetClienteByIdQuery implements Query<Cliente> {
    private String id;
}
