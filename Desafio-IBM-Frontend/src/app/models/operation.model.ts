import { Client } from "./client.model";
import { OperationType } from "./operation-type.model";

export interface Operation {
    date: Date;
    operationType: OperationType;
    value: number;
    client: Client;
}

export interface OperationDto {
    operationType: OperationType;
    value: number;
    clientId: string;
}