import { Component, OnInit } from '@angular/core';
import { ArtiklService } from '../../../services/artikl.service';
import { Artikl } from '../../../models/artikl';

@Component({
  selector: 'app-artikl',
  imports: [],
  templateUrl: './artikl.component.html',
  styleUrl: './artikl.component.css'
})
export class ArtiklComponent implements OnInit{

  artikli:Artikl[] = []

  constructor(private service: ArtiklService){}


  ngOnInit(): void {
    this.service.getAllArtikls().subscribe(
      {next: (data) => {this.artikli = data; console.log(data)}, 
      error: (err) => console.log(err)}
  
    )
  }

}
