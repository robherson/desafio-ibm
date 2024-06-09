import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Client } from '../models/client.model';
import { ClientService } from '../services/client.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-client-modal',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, ReactiveFormsModule, CommonModule],
  templateUrl: './add-client-modal.component.html',
  styleUrl: './add-client-modal.component.css'
})
export class AddClientModalComponent implements OnInit {

  clientForm!: FormGroup;
  
  @Input()
  actionType!: string;


  constructor(
    private clientService: ClientService,
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AddClientModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { operation: string, client: Client }
  ) { }


  ngOnInit(): void {
    
    if (this.data.operation === 'update'){
      this.clientForm = this.fb.group({
        id: [this.data.client?.id],
        name: [this.data.client?.name, Validators.required],
        age: [this.data.client?.age, Validators.required],
        email: [this.data.client?.email, [Validators.required, Validators.email]]
      });
    } else {
      this.clientForm = this.fb.group({
        name: ['', Validators.required],
        age: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]]
      });
    }

  }

  onSubmit(): void {
    if (this.clientForm.valid) {
      const clientData: Client = this.clientForm.value;
      if (this.data.operation == 'create'){
        this.clientService.addClient(clientData).subscribe( () => {
          this.dialogRef.close();
        });
      } else {
        this.clientService.updateClient(clientData).subscribe (() => {
          this.dialogRef.close();
        })
      }
    }
  }

  isDisabled(){
    return !this.clientForm.valid;
  }

  getTitle():string{
    return this.data.operation == 'create' ? 'Cadastrar Cliente' : 'Atualizar Cliente'
  }

  onCancel(): void {
    this.dialogRef.close(false);
  }



}
