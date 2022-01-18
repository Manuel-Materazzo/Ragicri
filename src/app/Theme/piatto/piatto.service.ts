import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
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

    getPiatti(): Observable<any[]> {
        return this.http.get<Piatto[]>(`${this.baseUrl}`).pipe(map((res: any) => {
            if (res) {
                return res;
            }
            return null;
        }));
    }

    getPiatto(numeretto: number): Observable<any> {
        return this.http.get((`${this.baseUrl}/getByNumero/${numeretto}`));
    }

    getTipologiaPiatto(tipologia: string): Observable<any> {
        return this.http.get((`${this.baseUrl}/tipologia/${tipologia}`));
    }

    //controlla se il numero del piatto esiste
    getEsiste(numeretto: number): Observable<any> {
        return this.http.get((`${this.baseUrl}/numero/${numeretto}`));
    }

    getPiattiFiltrati(getPiattoDto: getPiattoDto): Observable<any> {
        return this.http.post(`${this.baseUrl}/get`, getPiattoDto);
    }

    getTipologie():Observable<any>{
        return this.http.get(`${this.baseUrl}/tipologie`);
    }
    updatePiatto(Piatto: AddPiattoDTO): Observable<any> {
        return this.http.post(`${this.baseUrl}/update`, Piatto);
    }

    addPiatto(Piatto: AddPiattoDTO): Observable<any> {
        return this.http.post(`${this.baseUrl}/add`, Piatto);
    }

    deletePiatto(numerettoPiatto: any): Observable<any> {
        let piatto = '{ "numero": ' + numerettoPiatto + '}';
        return this.http.post(`${this.baseUrl}/delete`, JSON.parse(piatto));
    }
}
