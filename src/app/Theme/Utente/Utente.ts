import {PiattoOrdinato} from '../piattoOrdinato/PiattoOrdinato';
import {Indirizzo} from '../indirizzo/Indirizzo';
import {Role} from '../Role/role';

export class Utente{
    id: number;
    nome: string;
    email: string;
    username: string;
    password: string;
    indirizzo: Indirizzo;
    ruolo: Role;
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
