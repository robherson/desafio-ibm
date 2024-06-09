import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Client } from '../models/client.model';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from '../services/client.service';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { OperationService } from '../services/operation.service';
import { Operation } from '../models/operation.model';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { OperationType } from '../models/operation-type.model';
import { MatDialog } from '@angular/material/dialog';
import { ProcessOperationModalComponent } from '../process-operation-modal/process-operation-modal.component';

@Component({
  selector: 'app-client-details',
  standalone: true,
  imports: [CommonModule, MatTableModule, CommonModule],
  providers: [DatePipe, CurrencyPipe],
  templateUrl: './client-details.component.html',
  styleUrl: './client-details.component.css'
})
export class ClientDetailsComponent implements OnInit {

  client: Client | null = null;
  id!: string;
  operations: Operation[] = []

  displayedColumns: string[] = ['date', 'operationType', 'value'];
  dataSource: MatTableDataSource<Operation> = new MatTableDataSource<Operation>;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private opeartionService: OperationService,
    private cb: ChangeDetectorRef,
    private datePipe: DatePipe,
    private currencyPipe: CurrencyPipe,
    private dialog: MatDialog,
  ) { }


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];     
    });
    this.clientService.getClientById(this.id).subscribe(client => {
      this.client = client;
      this.getOperations();
    })
    
  }

  getOperations():void{
    if(this.client != null){
      this.opeartionService.getOperations(this.client.id).subscribe(operations => {
        this.operations = operations;
        this.dataSource = new MatTableDataSource(operations);
        this.cb.detectChanges();
      })
    }
  }

  formatDate(date: string): string | null {
    const dateV = new Date(date);
    return this.datePipe.transform(date, 'dd/MM/yyyy HH:mm:ss', 'local'); 
  }

  getOperatioText(opreation: OperationType): string{
      return opreation === OperationType.WITHDRAWAL ? "Saque" : "Depósito";
  }

  formatCoin(valor: number | undefined):string | null{
    return this.currencyPipe.transform(valor, 'BRL', 'symbol', '1.2-2');
  }

  addOperation(operationType: string){
    const operation = operationType === 'DEPOSIT' ? OperationType.DEPOSIT : OperationType.WITHDRAWAL; 
    this.dialog.open(ProcessOperationModalComponent, {
      data: {
        operationType: operation,
        client: this.client
       } 
    }).afterClosed().subscribe(() => {
      this.getOperations();
    });
  }

  

}
