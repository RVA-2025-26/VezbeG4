import { Component, Inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import {
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogModule,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { ArtiklService } from '../../../services/artikl.service';
import { Artikl } from '../../../models/artikl';

@Component({
  selector: 'app-artikl-dialog',
  imports: [MatDialogModule,MatFormFieldModule,MatButtonModule,
    MatInputModule, FormsModule],
  templateUrl: './artikl-dialog.component.html',
  styleUrl: './artikl-dialog.component.css'
})
export class ArtiklDialogComponent {

  flag!:number;

  constructor(private snackBar:MatSnackBar,
              private dialogRef:MatDialogRef<ArtiklDialogComponent>,
              private service:ArtiklService,
              @Inject(MAT_DIALOG_DATA) public data: Artikl){}

  public add():void{
    this.service.createArtikl(this.data).subscribe(
      {next: (data)=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Artikl with naziv: ${data.naziv} has been successfully created!`,
        'Okay', {duration:2500})
      },
      error: (err)=>{
        this.snackBar.open('Something went wrong during POST request!', 'Okay', {duration:2500});
        console.log(err.message);
      }}
    )
  }

  public update():void{
    this.service.updateArtikl(this.data).subscribe(
      {next: (data)=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Artikl with naziv: ${data.naziv} has been successfully updated!`,
        'Okay', {duration:2500})
      },
      error: (err)=>{
        this.snackBar.open('Something went wrong during PUT request!', 'Okay', {duration:2500});
        console.log(err.message);
      }}
    )
  }

  public delete():void{
    this.service.deleteArtikl(this.data.id).subscribe(
      {next: ()=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Artikl has been successfully deleted!`,
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
