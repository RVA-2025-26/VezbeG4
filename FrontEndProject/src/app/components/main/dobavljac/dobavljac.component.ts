import { Component } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialog } from '@angular/material/dialog';
import { DobavljacService } from '../../../services/dobavljac.service';
import { Dobavljac } from '../../../models/dobavljac';
import { DobavljacDialogComponent } from '../../dialogs/dobavljac-dialog/dobavljac-dialog.component';

@Component({
  selector: 'app-dobavljac',
  imports: [MatTableModule, MatIconModule, MatToolbarModule],
  templateUrl: './dobavljac.component.html',
  styleUrl: './dobavljac.component.css'
})
export class DobavljacComponent {

  displayedColumns = ['id', 'naziv', 'kontakt','adresa', 'actions'];
    dataSource!:MatTableDataSource<Dobavljac>;
  
    constructor(private service: DobavljacService, private dialog:MatDialog){}
  
  
    ngOnInit(): void {
      this.loadData();
    }
    
    public loadData():void{
      this.service.getAllDobavljacs().subscribe(
        {next: (data) => {this.dataSource = new MatTableDataSource<Dobavljac>(data)}, 
        error: (err) => console.log(err)}
    
      )
    }
  
    public openDialog(flag:number, id?:number, naziv?:string, kontakt?:string, adresa?:string):void{
      const ref = this.dialog.open(DobavljacDialogComponent, {data: {id,naziv,kontakt, adresa}});
      ref.componentInstance.flag = flag;
      ref.afterClosed().subscribe(
        (response) => {
          if(response === 1){
            this.loadData();
          }
        }
      )
    }

}
