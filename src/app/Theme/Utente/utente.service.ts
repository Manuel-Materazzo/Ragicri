import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Utente} from './Utente';

@Injectable({
  providedIn: 'root'
})
export class UtenteService {

  constructor(private http: HttpClient) { }

  private baseUrl= "http://localhost:8080/ragicri/utente";

  createUtente( utente: any): any {
    const data = utente;
    return this.http.post<any>('http://localhost:8080/ragicri/utente/save', data).pipe(map(r => {
      return r;
    }));
  }

  login(numeretto: number): Observable<any> {
    return this.http.get((`${this.baseUrl}/createAuthenticationToken/`));
  }

}
