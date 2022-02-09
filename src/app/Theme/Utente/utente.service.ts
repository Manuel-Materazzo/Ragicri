import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UtenteService {

    constructor(private http: HttpClient) {
    }

    private baseUrl = 'http://localhost:8080/ragicri/utente';
    private header = {
        headers: new HttpHeaders({Authorization: 'Bearer ' + sessionStorage.getItem('token')}),
    };

    getById(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${id}`, this.header);
    }

    getByUsername(username: string): Observable<any> {
        return this.http.get(`${this.baseUrl}/username/${username}`, this.header);
    }

    getEmail(username: string, token): Observable<any> {
        let header = {
            headers: new HttpHeaders({Authorization: 'Bearer ' +  token}),
        };
        return this.http.get(`${this.baseUrl}/email/${username}`, header);
    }

    registraUtente(addUtente: any): Observable<any> {
        return this.http.post(`${this.baseUrl}/add`, JSON.parse(addUtente));
    }

    updateUtente(utente: any): Observable<any> {
        return this.http.post(`${this.baseUrl}/update`, utente, this.header);
    }

    getAllUtentiAzienda(): Observable<any> {
        return this.http.get(`${this.baseUrl}/utentiAzienda`, this.header);
    }

    verificaPassword(verificaPassword: any): Observable<any> {
        return this.http.post(`${this.baseUrl}/checkPassword`, JSON.parse(verificaPassword), this.header);
    }

    getIdIndirizzoByUsername(username: string): Observable<any> {
        return this.http.get(`${this.baseUrl}/IdIndirizzo/${username}`, this.header);
    }

    consegnato(tavolo: string): Observable<any> {
        let dto = '{"tavolo": ' + tavolo + '}';
        return this.http.post(`${this.baseUrl}/consegnato`, JSON.parse(dto), this.header);
    }

    eliminaUtente(id: any): Observable<any> {
        let utente = '{ "numero": ' + id + '}';
        return this.http.post(`${this.baseUrl}/delete`, JSON.parse(utente), this.header);
    }

    authenticate(json): Observable<any> {
        return this.http.post('http://localhost:8080/ragicri/authenticate', JSON.parse(json));
    }

    inviaMail(email: String): Observable<any> {
        return this.http.get(`${this.baseUrl}/inviaMail/${email}`);
    }
}
