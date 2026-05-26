import { Porudzbina } from '../../../models/porudzbina';
import { PorudzbinaService } from '../../../services/porudzbina.service';
import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialog } from '@angular/material/dialog';
import { PorudzbinaDialogComponent } from '../../dialogs/porudzbina-dialog/porudzbina-dialog.component';
import { Dobavljac } from '../../../models/dobavljac';
import { StavkaPorudzbineComponent } from '../stavka-porudzbine/stavka-porudzbine.component';

@Component({
  selector: 'app-porudzbina',
  imports: [MatIconModule,MatToolbarModule,MatTableModule, DatePipe, StavkaPorudzbineComponent],
  templateUrl: './porudzbina.component.html',
  styleUrl: './porudzbina.component.css'
})
export class PorudzbinaComponent {
  displayedColumns = ['id', 'datumPorudzbine', 'datumIsporuke','iznos','placeno',
  'dobavljac', 'actions'];
  dataSource!:MatTableDataSource<Porudzbina>;

  parentSelectedPorudzbina!:Porudzbina;

  constructor(private service: PorudzbinaService, private dialog:MatDialog){}


  ngOnInit(): void {
    this.loadData();
  }
  
  public loadData():void{
    this.service.getAllPorudzbinas().subscribe(
      {next: (data) => {this.dataSource = new MatTableDataSource<Porudzbina>(data)}, 
      error: (err) => console.log(err)}
  
    )
  }

  public openDialog(flag:number, id?:number, datumPorudzbine?:Date, datumIsporuke?:Date,
     iznos?:number, placeno?:boolean, dobavljac?:Dobavljac):void{
    const ref = this.dialog.open(PorudzbinaDialogComponent, {data: {id,datumPorudzbine, datumIsporuke, iznos, placeno, dobavljac}});
    ref.componentInstance.flag = flag;
    ref.afterClosed().subscribe(
      (response) => {
        if(response === 1){
          this.loadData();
        }
      }
    )
  }

  public selectRow(row:Porudzbina){
    this.parentSelectedPorudzbina = row;
  }
}
