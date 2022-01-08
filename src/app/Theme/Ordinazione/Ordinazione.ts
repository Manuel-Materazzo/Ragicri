import {PiattoOrdinato} from '../piattoOrdinato/PiattoOrdinato';
import {PiattoInvio} from '../piatto/Piatto';

export class Ordinazione{
    idOrdinazione: number;
    tipologia: string;
    pagato: boolean;
    tavolo: number;
    persone: number;
    piattiOrdinati: PiattoOrdinato[];
}

export class OrdinazioneInvio{
     pagato: boolean;
     persone: number;
     piattiOrdinati: PiattoInvio[];
     tavolo: number;
     tipologia: string;

    constructor() {
    }
}
