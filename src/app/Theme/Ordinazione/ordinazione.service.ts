import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Ordinazione } from './Ordinazione';

@Injectable({
  providedIn: 'root'
})
export class OrdinazioneService {

  constructor(private http: HttpClient) { }

  private baseUrl = "http://localhost:8080/ragicri/ordinazione";
  private header = {
    headers: new HttpHeaders({ Authorization: "Bearer " + sessionStorage.getItem("token") }),
  };

  getOrdinazioni(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, this.header);
  }

  getNonPagato(): Observable<any> {
    return this.http.get(`${this.baseUrl}/nonPagato`, this.header);
  }

  getInfoTavolo(numeroTavolo: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/info/${numeroTavolo}`, this.header);
  }

  getInfoOrd(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/infoOrd/${id}`, this.header);
  }

  aggiungiOrdinazione(ordinazione: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/addOrdinazione`, ordinazione, this.header);
  }

  consegnato(tavolo: string): Observable<any> {
    let dto = "{\"tavolo\": " + tavolo + "}";
    return this.http.post(`${this.baseUrl}/consegnato`, JSON.parse(dto), this.header);
  }

  consegnatoId(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/consegnatoId/${id}`, this.header);
  }

  getAsportoDomicilio(): Observable<any> {
    return this.http.get(`${this.baseUrl}/asportoDomicilio`, this.header);
  }

  getAsportoDomicilioNonConsegnato(): Observable<any> {
    return this.http.get(`${this.baseUrl}/asportoDomicilioNonConsegnato`, this.header);
  }

  aggiungiOrdinazioneIndirizzo(ordinazione: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/addOrdinazioneIndirizzo`, ordinazione, this.header);
  }

  setConsegnato(idOrdinazione: any): Observable<any> {
    let json = "{\"idOrdinazione\": " + idOrdinazione + "}";
    console.log(json);
    return this.http.post(`${this.baseUrl}/setConsegnato`, JSON.parse(json), this.header);
  }

  setPagato(tavolo: any): Observable<any>{
    return this.http.get(`${this.baseUrl}/pagato/${tavolo}`, this.header);
  }
}