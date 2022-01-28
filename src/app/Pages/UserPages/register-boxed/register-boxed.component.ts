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

  utente: AddUtenteDTO = new AddUtenteDTO();
  url: string | ArrayBuffer = '';

  constructor(private utenteService: UtenteService, private router: Router, private modalService: NgbModal) {
  }

  ngOnInit() {
  }

  createUtente() {
    this.utente.nome = (document.getElementById('addNome') as HTMLInputElement).value;
    this.utente.email = (document.getElementById('addEmail') as HTMLInputElement).value;
    this.utente.username = (document.getElementById('addUsername') as HTMLInputElement).value;
    this.utente.password = (document.getElementById('addPassword') as HTMLInputElement).value;
    //this.utente.indirizzo = (document.getElementById('addIndirizzo') as HTMLInputElement).value;
    this.utente.ruolo = (document.getElementById('addRuolo') as HTMLInputElement).value;

    let input = new FormData();
    input.append('nome', this.utente.nome);
    input.append('email', this.utente.email + '');
    input.append('username', this.utente.username);
    input.append('password', this.utente.password);
    input.append('ruolo', this.utente.ruolo);
    this.utenteService.createUtente(input).subscribe((response: any) => {
    });
    this.url = '';
    this.modalService.dismissAll();
}

}
