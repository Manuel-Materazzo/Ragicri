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

/*
  getOrdinazioni(): Observable<any[]>{
    return this.http.get<Ordinazione[]>(`${this.baseUrl}`).pipe(map((res: any) => {
      if (res) {
        return res;
      }
      return null;
    }));
  }

  getNonPagato(): Observable<any>{
    return this.http.get(`${this.baseUrl}/nonPagato`);
  }

  getInfoTavolo(numeroTavolo: number): Observable<any>{
    return this.http.get(`${this.baseUrl}/info/${numeroTavolo}`);
  }

  aggiungiOrdinazione(ordinazione: any) :Observable<any>{
    return this.http.post(`${this.baseUrl}/addOrdinazione`, ordinazione);
  }

  consegnato(tavolo: string): Observable<any>{
    let dto = "{\"tavolo\": " + tavolo + "}";
    return this.http.post(`${this.baseUrl}/consegnato`, JSON.parse(dto));    
  }*/
}
