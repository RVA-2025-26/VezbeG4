import { Artikl } from "./artikl";
import { Porudzbina } from "./porudzbina";

export class StavkaPorudzbine{
    id!:number;
    redniBroj!:number;
    kolicina!:number;
    cena!:number;
    jedinicaMere!:String;
    artikl!:Artikl;
    porudzbina!:Porudzbina;
}