import { Component, Inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogModule,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DobavljacService } from '../../../services/dobavljac.service';
import { Dobavljac } from '../../../models/dobavljac';

@Component({
  selector: 'app-dobavljac-dialog',
  imports: [MatDialogModule, MatFormFieldModule, MatButtonModule, MatInputModule, FormsModule],
  templateUrl: './dobavljac-dialog.component.html',
  styleUrl: './dobavljac-dialog.component.css'
})
export class DobavljacDialogComponent {

  flag!:number;

  constructor(private snackBar:MatSnackBar,
              private dialogRef:MatDialogRef<DobavljacDialogComponent>,
              private service:DobavljacService,
              @Inject(MAT_DIALOG_DATA) public data: Dobavljac){}

  public add():void{
    this.service.createDobavljac(this.data).subscribe(
      {next: (data)=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Dobavljac with naziv: ${data.naziv} has been successfully created!`,
        'Okay', {duration:2500})
      },
      error: (err)=>{
        this.snackBar.open('Something went wrong during POST request!', 'Okay', {duration:2500});
        console.log(err.message);
      }}
    )
  }

  public update():void{
    this.service.updateDobavljac(this.data).subscribe(
      {next: (data)=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Dobavljac with naziv: ${data.naziv} has been successfully updated!`,
        'Okay', {duration:2500})
      },
      error: (err)=>{
        this.snackBar.open('Something went wrong during PUT request!', 'Okay', {duration:2500});
        console.log(err.message);
      }}
    )
  }

  public delete():void{
    this.service.deleteDobavljac(this.data.id).subscribe(
      {next: ()=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Dobavljac has been successfully deleted!`,
        'Okay', {duration:2500})
      },
      error: (err)=>{
        this.snackBar.open('Something went wrong during DELETE request!', 'Okay', {duration:2500});
        console.log(err.message);
      }}
    )
  }

  public cancel():void{
    this.dialogRef.close();
    this.snackBar.open(`You've given up on changes!`,
        'Okay', {duration:2500})
  }
}
