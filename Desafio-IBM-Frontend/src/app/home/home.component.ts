import { Component, OnInit } from '@angular/core';
import { Client } from '../models/client.model';
import { ClientService } from '../services/client.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { AddClientModalComponent } from '../add-client-modal/add-client-modal.component';
import { Router } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import { ConfirmDeleteComponent } from '../confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatTableModule, MatIconModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'age', 'email', 'accountNumber', 'actions'];
  dataSource: MatTableDataSource<Client> = new MatTableDataSource<Client>;

  
  constructor(
    private clientService: ClientService,
    private dialog: MatDialog,
    private router: Router
  ) { }
  
  ngOnInit(): void {
    this.getAllClients();
  }

  getAllClients(){
    this.clientService.getClients().subscribe(clients => {
      this.dataSource = new MatTableDataSource(clients);
    });
  }

  createClient(){
      this.dialog.open(AddClientModalComponent, {
        data: { operation: 'create' } 
      });
  }

  uptadeClient(client: Client){
    this.dialog.open(AddClientModalComponent, {
      data: { operation: 'update',
        client: client
       } 
    }).afterClosed().subscribe(() => {
      this.getAllClients();
    });
}

deleteClient(client: Client){
  this.dialog.open(ConfirmDeleteComponent, {
    data: {
      client: client
     } 
  }).afterClosed().subscribe(() => {
    this.getAllClients();
  });
}

  goToDetails(id: string): void {
    this.router.navigate(['/clients', id]);
  }
  

}
