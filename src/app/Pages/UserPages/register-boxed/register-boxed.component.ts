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
  ripetipass: String;
  textError: String;
  
  constructor(private utenteService: UtenteService, private router: Router, private modalService: NgbModal) {
  }

  ngOnInit() {
  }

  Login(){
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
    var textError = (document.getElementById('textError') as HTMLInputElement).innerText;
    console.log(textError);
    if(this.password==this.ripetipass){
      let json='{"email": "' + this.email + '", "indirizzoDTO": {"cap": ' + this.cap + ', "civico": '+ this.civico + ', "provincia": "'+ this.provincia + '", "via": "' + this.via + '" }, "nome": "' + this.nome + '", "password": "' + this.password + '", "ruolo": {"id": 3, "name": "ROLE_UTENTE"}, "username": "' + this.username +'"}';
      this.utenteService.registraUtente(json).subscribe(data1 =>{
        
        let json = '{"username": "' + this.username + '", "password": "' + this.password + '"}';
        this.utenteService.authenticate(json).subscribe(data => {
          
          if(data.token != null){
            sessionStorage.setItem("token", data.token);
            sessionStorage.setItem("username", String(this.username));
            window.location.replace("/");
          }
        });
      });
    }else{
      (document.getElementById('textError') as HTMLInputElement).innerHTML = 'Password inserita errata, riprova';
      document.getElementById('textError').style.color = '#FF0000';
    }

}

}
