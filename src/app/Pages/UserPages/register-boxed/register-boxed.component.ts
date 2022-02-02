import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddUtenteDTO } from 'src/app/Theme/Utente/Utente';
import { UtenteService } from 'src/app/Theme/Utente/utente.service';

@Component({
  selector: 'app-register-boxed',
  templateUrl: './register-boxed.component.html',
  styleUrls: ['./register-boxed.scss']
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
  ripetipass: String;
  textError: String;
  controllo: boolean;
  checkBox;

  constructor(private utenteService: UtenteService, private router: Router, private modalService: NgbModal) {
  }

  ngOnInit() {
  }

  Login() {
    window.location.replace("/pages/login-boxed");
  }

  Registrazione() {
    this.nome = (document.getElementById('nome') as HTMLInputElement).value;
    this.email = (document.getElementById('email') as HTMLInputElement).value;
    this.username = (document.getElementById('username') as HTMLInputElement).value;
    this.password = (document.getElementById('password') as HTMLInputElement).value;
    this.ripetipass = (document.getElementById('ripetiPassword') as HTMLInputElement).value;
    this.via = (document.getElementById('via') as HTMLInputElement).value;
    this.provincia = (document.getElementById('provincia') as HTMLInputElement).value;
    this.cap = parseInt((document.getElementById('cap') as HTMLInputElement).value);
    this.civico = parseInt((document.getElementById('civico') as HTMLInputElement).value);
    this.checkBox = (document.getElementById('checkTermini') as HTMLInputElement).checked;
    console.log(this.checkBox);

    if (this.ControlloDati()) {
      let json = '{"email": "' + this.email + '", "indirizzoDTO": {"cap": ' + this.cap + ', "civico": ' + this.civico + ', "provincia": "' + this.provincia + '", "via": "' + this.via + '" }, "nome": "' + this.nome + '", "password": "' + this.password + '", "ruolo": "ROLE_UTENTE", "username": "' + this.username + '"}';
      this.utenteService.registraUtente(json).subscribe(data1 => {

        let json = '{"username": "' + this.username + '", "password": "' + this.password + '"}';
        this.utenteService.authenticate(json).subscribe(data => {

          if (data.token != null) {
            sessionStorage.setItem("token", data.token);
            sessionStorage.setItem("username", String(this.username));
            window.location.replace("/");
          }
          });
      }, error => {
        if("{\"status\": \"Username già in uso.\"}"){
          document.getElementById('erroreUsername').removeAttribute('hidden');
          (document.getElementById('username') as HTMLInputElement).value = "";
          (document.getElementById('password') as HTMLInputElement).value = "";
          (document.getElementById('ripetiPassword') as HTMLInputElement).value = "";
        }
      });

    } else {
      document.getElementById('erroreDati').removeAttribute('hidden');
    }
  }

  ControlloDati() {
    this.controllo = true;

    document.getElementById('erroreUsername').setAttribute('hidden', '');
    document.getElementById('errorePass').setAttribute('hidden', '');
    document.getElementById('erroreTermini').setAttribute('hidden', '');
    
    if (this.nome == "")
      return false;
    if (this.email == "")
      return false;
    if (this.provincia == "")
      return false;
    if (this.via == "")
      return false;
    if (this.civico == null)
      return false;
    if (this.cap == null)
      return false;
    if (this.username == "")
      return false;
    if (this.password == "")
      return false;
    if (this.ripetipass == "")
      return false;
    
    if (this.password != this.ripetipass) {
      document.getElementById('errorePass').removeAttribute('hidden');
      (document.getElementById('password') as HTMLInputElement).value = "";
      (document.getElementById('ripetiPassword') as HTMLInputElement).value = "";
      return false;
    }
    else{
      
    }
    if (this.checkBox == false) {
      document.getElementById('erroreTermini').removeAttribute('hidden');
      return false;
    }

    return true;
  }

}
