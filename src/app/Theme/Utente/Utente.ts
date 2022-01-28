import {PiattoOrdinato} from '../piattoOrdinato/PiattoOrdinato';
import {Indirizzo} from '../indirizzo/Indirizzo';

export class Utente{
    idUtente: number;
    nome: string;
    email: string;
    username: string;
    password: string;
    indirizzo: Indirizzo;
    ruolo: string;
}

export class UtenteLogin{
    username: string;
    password: string;
}

export class AddUtenteDTO {
    nome: string;
    email: string;
    username: string;
    password: string;
    indirizzo: Indirizzo;
    ruolo: string;

    constructor() {
    }
}
