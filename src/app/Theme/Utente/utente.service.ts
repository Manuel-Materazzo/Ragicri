import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Utente} from './Utente';

@Injectable({
  providedIn: 'root'
})
export class UtenteService {

  constructor(private http: HttpClient) { }

  private baseUrl= "http://localhost:8080/ragicri/utente";
  private header = {                                                                                                                                                                                 
      headers: new HttpHeaders({ Authorization: "Bearer " + sessionStorage.getItem("token")}), 
  };

  registraUtente(json): Observable<any>{
    return this.http.post("http://localhost:8080/ragicri/utente/add", JSON.parse(json));
  }

  getNonPagato(): Observable<any>{
    return this.http.get(`${this.baseUrl}/nonPagato`, this.header);
  }

  getInfoTavolo(numeroTavolo: number): Observable<any>{
    return this.http.get(`${this.baseUrl}/info/${numeroTavolo}`, this.header);
  }

  getIndirizzoByUsername(username: string): Observable<any>{
    return this.http.get(`${this.baseUrl}/indirizzo/${username}`, this.header);
  }

  aggiungiOrdinazione(ordinazione: any) :Observable<any>{
    return this.http.post(`${this.baseUrl}/addOrdinazione`, ordinazione, this.header);
  }

  consegnato(tavolo: string): Observable<any>{
    let dto = "{\"tavolo\": " + tavolo + "}";
    return this.http.post(`${this.baseUrl}/consegnato`, JSON.parse(dto), this.header);    
  }

  authenticate(json): Observable<any>{
    return this.http.post("http://localhost:8080/ragicri/authenticate", JSON.parse(json));
  } 
}
