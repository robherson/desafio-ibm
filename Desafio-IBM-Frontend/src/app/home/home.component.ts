import { Component, OnInit } from '@angular/core';
import { Client } from '../models/client.model';
import { ClientService } from '../services/client.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatTableModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  displayedColumns: string[] = ['name', 'age', 'email', 'accountNumber'];
  dataSource: MatTableDataSource<Client> = new MatTableDataSource<Client>;

  
  constructor(private clientService: ClientService) { }
  
  ngOnInit(): void {
    this.clientService.getClients().subscribe(clients => {
      this.dataSource = new MatTableDataSource(clients);
    });
  }
  

}
