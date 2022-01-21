import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Ordinazione} from './Ordinazione';

@Injectable({
  providedIn: 'root'
})
export class OrdinazioneService {

  constructor(private http: HttpClient) { }

  private baseUrl= "http://localhost:8080/ragicri/ordinazione";


  getOrdinazioni(): Observable<any[]>{
    return this.http.get<Ordinazione[]>(`${this.baseUrl}`).pipe(map((res: any) => {
      if (res) {
        return res;
      }
      return null;
    }));
  }

  getNonPagato(): Observable<any>{
    return this.http.get((`${this.baseUrl}/nonPagato`));
  }

  getInfoTavolo(numeroTavolo: number): Observable<any>{
    return this.http.get((`${this.baseUrl}/info/${numeroTavolo}`));
  }

  getInfoOrd(id: number): Observable<any>{
    return this.http.get(`${this.baseUrl}/infoOrd/${id}`);
  }

  aggiungiOrdinazione(ordinazione: any) :Observable<any>{
    return this.http.post(`${this.baseUrl}/addOrdinazione`, ordinazione);
  }

  consegnato(tavolo: string): Observable<any>{
    let dto = "{\"tavolo\": " + tavolo + "}";
    return this.http.post(`${this.baseUrl}/consegnato`, JSON.parse(dto));    
  }

  consegnatoId(id: number): Observable<any>{
    return this.http.get(`${this.baseUrl}/consegnatoId/${id}`);    
  }

  getAsportoDomicilio(): Observable<any>{
    return this.http.get(`${this.baseUrl}/asportoDomicilio`);
  }

  aggiungiOrdinazioneIndirizzo(ordinazione: any) :Observable<any>{
    return this.http.post(`${this.baseUrl}/addOrdinazioneIndirizzo`, ordinazione);
  }
}
