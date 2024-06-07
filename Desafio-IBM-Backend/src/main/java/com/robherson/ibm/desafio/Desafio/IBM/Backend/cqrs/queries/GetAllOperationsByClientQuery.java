package com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.queries;

import java.util.List;

import com.robherson.ibm.desafio.Desafio.IBM.Backend.cqrs.Query;
import com.robherson.ibm.desafio.Desafio.IBM.Backend.models.Operation;

import lombok.Data;

@Data
public class GetAllOperationsByClientQuery implements Query<List<Operation>> {

    private String clientId;
    
}
