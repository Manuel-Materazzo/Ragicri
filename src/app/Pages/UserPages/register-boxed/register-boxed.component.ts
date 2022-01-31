import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddUtenteDTO } from 'src/app/Theme/Utente/Utente';
import { UtenteService } from 'src/app/Theme/Utente/utente.service';

@Component({
  selector: 'app-register-boxed',
  templateUrl: './register-boxed.component.html',
  styles: []
})
export class RegisterBoxedComponent implements OnInit {

  url: string | ArrayBuffer = '';
  nome: String;
  email: String;
  username: String;
  password: String;
  via: String;
  provincia: String;
  cap: number;
  civico: number;

   

  constructor(private utenteService: UtenteService, private router: Router, private modalService: NgbModal) {
  }

  ngOnInit() {
  }

  Registrazione() {
    this.nome = (document.getElementById('nome') as HTMLInputElement).value;
    this.email = (document.getElementById('email') as HTMLInputElement).value;
    this.username = (document.getElementById('username') as HTMLInputElement).value;
    this.password = (document.getElementById('password') as HTMLInputElement).value;
    this.via = (document.getElementById('via') as HTMLInputElement).value;
    this.provincia = (document.getElementById('provincia') as HTMLInputElement).value;
    this.cap = parseInt((document.getElementById('cap') as HTMLInputElement).value);
    this.civico = parseInt((document.getElementById('civico') as HTMLInputElement).value);

    let json='{ "indirizzoDTO": {"cap": ' + this.cap + ', "civico": '+ this.civico + ', "provincia:" "'+ this.provincia + '", "via": "' + this.via + '" }, "nome": "' + this.nome + '", "password": "' + this.password + '", "ruolo": {"id": 1, "name": "ROLE_ADMIN"}, "username": "' + this.username +'"}';
    this.utenteService.registraUtente(json).subscribe(data =>{ 
      
      let json = '{"username": "' + this.username + '", "password": "' + this.password + '"}';
      this.utenteService.authenticate(json).subscribe(data => {
        
        if(data.token != null){
          sessionStorage.setItem("token", data.token);
          sessionStorage.setItem("username", String(this.username));
          window.location.replace("http://localhost:4200/");
        }
      });
    });

}

}
