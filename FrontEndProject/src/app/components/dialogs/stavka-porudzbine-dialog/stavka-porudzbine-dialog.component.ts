import { Artikl } from '../../../models/artikl';
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
import {MatSelectModule} from '@angular/material/select';
import { StavkaPorudzbineService } from '../../../services/stavka-porudzbine.service';
import { StavkaPorudzbine } from '../../../models/stavka-porudzbine';
import { ArtiklService } from '../../../services/artikl.service';

@Component({
  selector: 'app-stavka-porudzbine-dialog',
  imports: [MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule, FormsModule,MatSelectModule],
  templateUrl: './stavka-porudzbine-dialog.component.html',
  styleUrl: './stavka-porudzbine-dialog.component.css'
})
export class StavkaPorudzbineDialogComponent implements OnInit {

  flag!:number;
  artikli!:Artikl[];

  constructor(private snackBar:MatSnackBar,
              private dialogRef:MatDialogRef<StavkaPorudzbineDialogComponent>,
              private service:StavkaPorudzbineService,
              @Inject(MAT_DIALOG_DATA) public data: StavkaPorudzbine,
              private artiklService: ArtiklService
              ){}
  
  
  ngOnInit(): void {
    this.artiklService.getAllArtikls().subscribe(
      (data) => this.artikli = data
    )
  }

  public add():void{
    this.service.createStavkaPorudzbine(this.data).subscribe(
      {next: (data)=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Stavka porudzbine with id: ${data.id} has been successfully created!`,
        'Okay', {duration:2500})
      },
      error: (err)=>{
        this.snackBar.open('Something went wrong during POST request!', 'Okay', {duration:2500});
        console.log(err.message);
      }}
    )
  }

  public update():void{
    this.service.updateStavkaPorudzbine(this.data).subscribe(
      {next: (data)=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Stavka porudzbine with id: ${data.id} has been successfully updated!`,
        'Okay', {duration:2500})
      },
      error: (err)=>{
        this.snackBar.open('Something went wrong during PUT request!', 'Okay', {duration:2500});
        console.log(err.message);
      }}
    )
  }

  public delete():void{
    this.service.deleteStavkaPorudzbine(this.data.id).subscribe(
      {next: ()=> {
        this.dialogRef.close(1);
        this.snackBar.open(`Stavka porudzbine has been successfully deleted!`,
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
