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

export class getPiattoDto {
    tipologia: string;
    allergeni: string;

    public constructor(tip:string,aller:string) {
        this.tipologia=tip;
        this.allergeni=aller;
    }
    public  getAllergeni():string{
        return this.allergeni;
    }
    public  getTipologia():string{
        return this.tipologia;
    }
    public setTipologia(tip: string) {
        this.tipologia=tip;
    }
    public setAllergeni(aller: string){
        this.allergeni=aller;
    }
}
