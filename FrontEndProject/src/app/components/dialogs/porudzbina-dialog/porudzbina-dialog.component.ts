import { Component, Inject, OnInit } from '@angular/core';
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
import { PorudzbinaService } from '../../../services/porudzbina.service';
import { Porudzbina } from '../../../models/porudzbina';
import { Dobavljac } from '../../../models/dobavljac';
import { DobavljacService } from '../../../services/dobavljac.service';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatDatepickerModule, } from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';

@Component({
  selector: 'app-porudzbina-dialog',
  imports: [MatDialogModule,MatFormFieldModule,MatButtonModule,MatInputModule,FormsModule,
  MatCheckboxModule, MatDatepickerModule, MatNativeDateModule, MatSelectModule],
  templateUrl: './porudzbina-dialog.component.html',
  styleUrl: './porudzbina-dialog.component.css'
})
export class PorudzbinaDialogComponent implements OnInit {

  flag!:number;
  dobavljaci!:Dobavljac[];

  constructor(private snackBar:MatSnackBar,
              private dialogRef:MatDialogRef<PorudzbinaDialogComponent>,
              private service:PorudzbinaService,
              @Inject(MAT_DIALOG_DATA) public data: Porudzbina,
              private dobavljacService: DobavljacService
              ){}
  
  
  ngOnInit(): void {
    this.dobavljacService.getAllDobavljacs().subscribe(
      (data) => this.dobavljaci = data
    )
  }

  public add():void{
    this.service.createPorudzbina(this.data).subscribe(
      {next: (data)=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Porudzbina with id: ${data.id} has been successfully created!`,
        'Okay', {duration:2500})
      },
      error: (err)=>{
        this.snackBar.open('Something went wrong during POST request!', 'Okay', {duration:2500});
        console.log(err.message);
      }}
    )
  }

  public update():void{
    this.service.updatePorudzbina(this.data).subscribe(
      {next: (data)=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Porudzbina with id: ${data.id} has been successfully updated!`,
        'Okay', {duration:2500})
      },
      error: (err)=>{
        this.snackBar.open('Something went wrong during PUT request!', 'Okay', {duration:2500});
        console.log(err.message);
      }}
    )
  }

  public delete():void{
    this.service.deletePorudzbina(this.data.id).subscribe(
      {next: ()=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Porudzbina has been successfully deleted!`,
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

  public compare(a:any, b:any){
    return a.id == b.id;
  }
}
