import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UtenteService } from 'src/app/Theme/Utente/utente.service';

@Component({
  selector: 'app-login-boxed',
  templateUrl: './login-boxed.component.html',
  styles: []
})
export class LoginBoxedComponent implements OnInit {

  username: String;
  password: String;


  constructor(private router: Router, private utenteService: UtenteService, private modalService: NgbModal) {
  }

  ngOnInit() {
  }

  Login() {
    this.username = (document.getElementById('username') as HTMLInputElement).value;
    this.password = (document.getElementById('password') as HTMLInputElement).value;
      
    let json = '{"username": "' + this.username + '", "password": "' + this.password + '"}';

    this.utenteService.authenticate(json).subscribe(data =>{
      if(data.token != null){
        sessionStorage.setItem("token", data.token);
        sessionStorage.setItem("username", String(this.username));
        window.location.replace("/");
      }
      else{

      }
    });
  }

  Registrati(){
    window.location.replace("/pages/register-boxed");
  }
  

}
