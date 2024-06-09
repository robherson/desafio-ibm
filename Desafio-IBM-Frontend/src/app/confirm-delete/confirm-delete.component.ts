import { Component, Inject, OnInit } from '@angular/core';
import { Client } from '../models/client.model';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-confirm-delete',
  standalone: true,
  imports: [],
  templateUrl: './confirm-delete.component.html',
  styleUrl: './confirm-delete.component.css'
})

export class ConfirmDeleteComponent implements OnInit {

  client!: Client;

  constructor(
    private clientService: ClientService,
    private dialogRef: MatDialogRef<ConfirmDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { client: Client }
  ){}
  
  
  ngOnInit(): void {
    
  }

  onCancel(): void {
    this.dialogRef.close(false);
  }

  onConfirm(): void {
    this.clientService.deleteClient(this.data.client.id).subscribe(() => {
      this.dialogRef.close(true);
    })

  }

}
