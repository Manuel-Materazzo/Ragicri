import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class IndirizzoService {

  constructor(private http: HttpClient) { }

  private baseUrl= "http://localhost:8080/ragicri/indirizzo";

  private header = {
    headers: new HttpHeaders({ Authorization: "Bearer " + sessionStorage.getItem("token")}),
  };


}
