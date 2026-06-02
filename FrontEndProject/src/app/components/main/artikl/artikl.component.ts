import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ArtiklService } from '../../../services/artikl.service';
import { Artikl } from '../../../models/artikl';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialog } from '@angular/material/dialog';
import { ArtiklDialogComponent } from '../../dialogs/artikl-dialog/artikl-dialog.component';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';


@Component({
  selector: 'app-artikl',
  imports: [MatTableModule, MatIconModule, MatToolbarModule, MatSortModule, MatPaginatorModule],
  templateUrl: './artikl.component.html',
  styleUrl: './artikl.component.css'
})
export class ArtiklComponent implements OnInit, AfterViewInit{

  displayedColumns = ['id', 'naziv', 'proizvodjac', 'actions'];
  dataSource:MatTableDataSource<Artikl> = new MatTableDataSource<Artikl>([]);

  @ViewChild(MatSort) sort!:MatSort;
  @ViewChild(MatPaginator) paginator!:MatPaginator

  constructor(private service: ArtiklService, private dialog:MatDialog){}
  
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator
  }


  ngOnInit(): void {
    this.loadData();
  }
  
  public loadData():void{
    this.service.getAllArtikls().subscribe(
      {next: (data) => {this.dataSource.data = data}, 
      error: (err) => console.log(err)}
  
    )
  }

  public openDialog(flag:number, id?:number, naziv?:string, proizvodjac?:string):void{
    const ref = this.dialog.open(ArtiklDialogComponent, {data: {id,naziv,proizvodjac}});
    ref.componentInstance.flag = flag;
    ref.afterClosed().subscribe(
      (response) => {
        if(response){
          this.loadData();
        }
      }
    )
  }

}
