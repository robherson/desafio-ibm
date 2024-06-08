import { Component, OnInit } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Client } from '../models/client.model';
import { ClientService } from '../services/client.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-client-modal',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, ReactiveFormsModule],
  templateUrl: './add-client-modal.component.html',
  styleUrl: './add-client-modal.component.css'
})
export class AddClientModalComponent implements OnInit {

  clientForm!: FormGroup;


  constructor(
    private clientService: ClientService,
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AddClientModalComponent>
  ) { }


  ngOnInit(): void {
    this.clientForm = this.fb.group({
      name: ['', Validators.required],
      age: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit(): void {
    if (this.clientForm.valid) {
      const clientData: Client = this.clientForm.value;
      this.clientService.addClient(clientData).subscribe( () => {
        this.dialogRef.close();
      });
    }
  }

  isDisabled(){
    return !this.clientForm.valid;
  }



}
