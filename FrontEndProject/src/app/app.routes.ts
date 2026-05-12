import { Routes } from '@angular/router';
import { ArtiklComponent } from './components/main/artikl/artikl.component';
import { DobavljacComponent } from './components/main/dobavljac/dobavljac.component';
import { PorudzbinaComponent } from './components/main/porudzbina/porudzbina.component';
import { StavkaPorudzbineComponent } from './components/main/stavka-porudzbine/stavka-porudzbine.component';
import { AboutComponent } from './components/utility/about/about.component';
import { AuthorComponent } from './components/utility/author/author.component';
import { HomeComponent } from './components/utility/home/home.component';

export const routes: Routes = [
    {path: 'artikl', component:ArtiklComponent},
    {path: 'dobavljac', component:DobavljacComponent},
    {path: 'porudzbina', component:PorudzbinaComponent},
    {path: 'stavka-porudzbine', component:StavkaPorudzbineComponent},
    {path: 'author', component:AuthorComponent},
    {path: 'about', component:AboutComponent},
    {path: '', component:HomeComponent, pathMatch:"full"}
];
