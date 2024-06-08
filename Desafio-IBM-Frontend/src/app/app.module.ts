import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddClientModalComponent } from './add-client-modal/add-client-modal.component';
import { DatePipe } from '@angular/common';


@NgModule({
  declarations: [],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    AddClientModalComponent,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [DatePipe],
  bootstrap: []
})
export class AppModule { }
