import {Piatto} from '../piatto/Piatto';
import {Ordinazione} from '../Ordinazione/Ordinazione';

export class PiattoOrdinato {

    id: number;
    ordinazione: Ordinazione;
    piatto: Piatto;
    consegnato: boolean;
    quantita: number;
}

