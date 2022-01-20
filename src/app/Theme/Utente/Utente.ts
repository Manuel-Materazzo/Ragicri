import {PiattoOrdinato} from '../piattoOrdinato/PiattoOrdinato';
import {Indirizzo} from '../indirizzo/Indirizzo';

export class Utente{
    idUtente: number;
    nome: string;
    ruolo: string;
    username: string;
    password: string;
    indirizzo: Indirizzo;
}
