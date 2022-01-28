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
    
        
}
  

}
