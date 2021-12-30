import {PiattoOrdinato} from '../piattoOrdinato/PiattoOrdinato';

export class Ordinazione{
    idOrdinazione: number;
    tipologia: string;
    pagato: number;
    tavolo: number;
    persone: number;
    piattiOrdinati: PiattoOrdinato[];
}
