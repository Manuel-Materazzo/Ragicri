import { PiattoOrdinato}  from "../piattoOrdinato/PiattoOrdinato";

export class Piatto{
    idPiatto: number;
    nome: string;
    numero: number;
    tipologia: string;
    prezzo: number;
    allergeni: string;
    img: string;
    lista_PiattiOrdinati: PiattoOrdinato[];
}
