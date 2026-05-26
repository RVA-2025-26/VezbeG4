import { Dobavljac } from "./dobavljac";

export class Porudzbina{
    id!:number;
    datumPorudzbine!:Date;
    datumIsporuke!:Date;
    placeno!:boolean;
    iznos!:number;
    dobavljac!:Dobavljac;
}