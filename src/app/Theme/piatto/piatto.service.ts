import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AddPiattoDTO, getPiattoDto, Piatto} from './Piatto';
import {map} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class PiattoService {

    constructor(private http: HttpClient) {
    }

    private baseUrl = 'http://localhost:8080/ragicri/piatto';
    private header = {                                                                                                                                                                                 
        headers: new HttpHeaders({ Authorization: "Bearer " + sessionStorage.getItem("token")}), 
    };

    getPiatti(): Observable<any> {
        return this.http.get(this.baseUrl, this.header);
    }

    getPiatto(numeretto: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/getByNumero/${numeretto}`, this.header);
    }

    getTipologiaPiatto(tipologia: string): Observable<any> {
        return this.http.get(`${this.baseUrl}/tipologia/${tipologia}`, this.header);
    }

    //controlla se il numero del piatto esiste
    getEsiste(numeretto: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/numero/${numeretto}`, this.header);
    }

    getPiattiFiltrati(getPiattoDto: getPiattoDto): Observable<any> {
        return this.http.post(`${this.baseUrl}/get`, getPiattoDto, this.header);
    }

    getTipologie():Observable<any>{
        return this.http.get(`${this.baseUrl}/tipologie`, this.header);
    }

    updatePiattoConFoto(piatto: FormData): Observable<any> {
        return this.http.post(`${this.baseUrl}/updateConFoto`, piatto, this.header);
    }

    updatePiatto(piatto: AddPiattoDTO): Observable<any> {
        return this.http.post(`${this.baseUrl}/update`, piatto, this.header);
    }

    addPiatto(piatto: FormData): Observable<any> {
        return this.http.post(`${this.baseUrl}/add`, piatto, this.header);
    }

    deletePiatto(numerettoPiatto: any): Observable<any> {
        let piatto = '{ "numero": ' + numerettoPiatto + '}';
        return this.http.post(`${this.baseUrl}/delete`, JSON.parse(piatto), this.header);
    }
}
