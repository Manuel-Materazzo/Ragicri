import {PiattoOrdinato} from '../piattoOrdinato/PiattoOrdinato';

export class Piatto {
    idPiatto: number;
    nome: string;
    numero: number;
    tipologia: string;
    prezzo: number;
    allergeni: string;
    img: string;
    lista_PiattiOrdinati: PiattoOrdinato[];
}

export class getPiattoDto {
    tipologia: string;
    allergeni: string;

    public constructor(tip: string, aller: string) {
        this.tipologia = tip;
        this.allergeni = aller;
    }

    public setTipologia(tip: string) {
        this.tipologia = tip;
    }

    public setAllergeni(aller: string) {
        this.allergeni = aller;
    }
}

export class PiattoInvio {
    numeretto: number;
    quantita: number;

    constructor(numeretto: number, quantita: number) {
        this.numeretto = numeretto;
        this.quantita = quantita;
    }
}

export class AddPiattoDTO {
    nome: string;
    numero: number;
    tipologia: string;
    prezzo: number;
    allergeni: string;
    img: string;


    constructor() {
    }
}

export class TipologieDTO {
    tipologie:string[];
}
