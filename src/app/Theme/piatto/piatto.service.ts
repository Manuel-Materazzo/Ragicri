import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {getPiattoDto, Piatto} from './Piatto';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PiattoService {

  constructor(private http: HttpClient) { }

  private baseUrl= "http://localhost:8080/ragicri/piatto";

  getPiatti(): Observable<any[]>{
    return this.http.get<Piatto[]>(`${this.baseUrl}`).pipe(map((res: any) => {
      if (res) {
        return res;
      }
      return null;
    }));
  }
  getPiatto(id_piatto: number): Observable<any>{
    return this.http.get((`${this.baseUrl}/${id_piatto}`));
  }

  getTipologiaPiatto(tipologia: string): Observable<any>{
    return this.http.get((`${this.baseUrl}/tipologia/${tipologia}`));
  }

  //controlla se il numero del piatto esiste
  getEsiste(numeretto: number): Observable<any>{
    return this.http.get((`${this.baseUrl}/numero/${numeretto}`));
  }

  updatePiatto(id_piatto, Piatto): Observable<any>{
    return this.http.post(`${this.baseUrl}/${id_piatto}`,Piatto);
  }

  //con json non oggetto
  getPiattiFiltrati(getPiattoDto: getPiattoDto): Observable<any>{
    return this.http.post(`${this.baseUrl}/get`, getPiattoDto);
  }
  delete(id_piatto: string): Observable<any>{
    // @ts-ignore
    //TODO da provare potrebbere esserci un errore
    return this.http.delete(`${this.baseUrl}/delete`,"{" + id_piatto + "}");
  }


}
