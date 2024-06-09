import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Operation, OperationDto } from '../models/operation.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OperationService {
  private apiUrl = 'http://localhost:8080/api/operations';

  constructor(private http: HttpClient) { }

  getOperations(clientId: string): Observable<Operation[]> {
    return this.http.get<Operation[]>(`${this.apiUrl}/extract/${clientId}`);
  }

  addOperation(operation: OperationDto): Observable<string> {
    return this.http.post<string>(this.apiUrl, operation);
  }

}
