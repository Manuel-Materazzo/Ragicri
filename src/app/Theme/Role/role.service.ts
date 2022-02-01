import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { }

  private baseUrl= "http://localhost:8080/ragicri/role";
  private header = {
    headers: new HttpHeaders({ Authorization: "Bearer " + sessionStorage.getItem("token")}),
  };
  getAllRuoli(): Observable<any>{
    return this.http.get(`${this.baseUrl}`,this.header);
  }
}
