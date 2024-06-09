import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Client } from '../models/client.model';
import { OperationType } from '../models/operation-type.model';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { OperationDto } from '../models/operation.model';
import { OperationService } from '../services/operation.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-process-operation-modal',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, ReactiveFormsModule, CommonModule],
  templateUrl: './process-operation-modal.component.html',
  styleUrl: './process-operation-modal.component.css'
})
export class ProcessOperationModalComponent implements OnInit {

  operationForm!: FormGroup;

  operation!: OperationDto;
  
  constructor(
    private fb: FormBuilder,
    private operationService: OperationService,
    private dialogRef: MatDialogRef<ProcessOperationModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { client: Client, operationType: OperationType }
  ){}

  ngOnInit(): void {
    this.operationForm = this.fb.group({
      operationType: [this.data.operationType, Validators.required],
      clientId: [this.data.client?.id, Validators.required],
      value: ['', [Validators.required, Validators.min(1), this.isWithdrawal()? Validators.max(this.data.client.balance): Validators.min(1)]]
    })    
  }

  getTitle(){
    return this.isWithdrawal() ? 'Sacar' : 'Depositar';
  }

  onSubmit(){
    if (this.operationForm.valid) {
      const operationData: OperationDto = this.operationForm.value;
      this.operationService.addOperation(operationData).subscribe(() => {
        this.dialogRef.close(true);
      })
    }
    
  }

  isWithdrawal(){
    return this.data.operationType === OperationType.WITHDRAWAL; 
  }

  isDisabled(){
    return !this.operationForm.valid;
  }

  onCancel(): void {
    this.dialogRef.close(false);
  }


}
