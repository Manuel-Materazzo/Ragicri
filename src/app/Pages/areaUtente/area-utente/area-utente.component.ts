import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UtenteService} from '../../../Theme/Utente/utente.service';
import {UpdateUtenteDTO, Utente, UtentePassword} from '../../../Theme/Utente/Utente';
import {Indirizzo} from '../../../Theme/Indirizzo/Indirizzo';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-area-utente',
  templateUrl: './area-utente.component.html',
  styleUrls: ['./area-utente.component.scss']
})
export class AreaUtenteComponent implements OnInit {

  utente:Utente= new Utente();
  resultControllo: boolean;
  bodyUpdateUtente: UpdateUtenteDTO= new UpdateUtenteDTO();
  indirizzo:Indirizzo = new Indirizzo();
  utentePassword: UtentePassword= new UtentePassword();

  constructor(private router: Router,private utenteService:UtenteService,private modalService: NgbModal) {
    if (sessionStorage.getItem("token") == null) {
      window.location.replace("/");
    }
    if (this.parseJwt(sessionStorage.getItem("token")).roles.id != 3) {
      window.location.replace("/");
    }
  }

  ngOnInit() {
    this.utenteService.getByUsername(sessionStorage.getItem("username"))
        .subscribe(async(response: any) => {
          this.utente=response;
          console.log(this.utente.indirizzo.cap);
          (document.getElementById('nome') as HTMLInputElement).value = this.utente.nome;
          (document.getElementById('email') as HTMLInputElement).value = this.utente.email;
          (document.getElementById('via') as HTMLInputElement).value = this.utente.indirizzo.via;
          (document.getElementById('cap') as HTMLInputElement).value = this.utente.indirizzo.cap + '';
          (document.getElementById('provincia') as HTMLInputElement).value = this.utente.indirizzo.provincia;
          (document.getElementById('civico') as HTMLInputElement).value = this.utente.indirizzo.civico;

          await this.sleep(200);

        });

  }

  //funzione per il model di conferma
  openConfermaModal(content) {
    this.modalService.open(content, {
      size: 'sm'
    });
  }

  //funzione per il model modifica
  openCambiaPasswordModal(content) {
      this.modalService.open(content, {
        size: 'lg'
      });

  }
  parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
  };
  sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  modificaUtente() {
    if (this.controlloInputUpdate()) {
      return;
    }
    this.bodyUpdateUtente.id=this.utente.id;
    this.bodyUpdateUtente.nome=(document.getElementById('nome') as HTMLInputElement).value;
    this.bodyUpdateUtente.email=(document.getElementById('email') as HTMLInputElement).value;
    this.bodyUpdateUtente.password=null;
    this.bodyUpdateUtente.username=this.utente.username;
    this.indirizzo.idIndirizzo=this.utente.indirizzo.idIndirizzo;
    this.indirizzo.via=(document.getElementById('via') as HTMLInputElement).value;
    this.indirizzo.cap=parseInt((document.getElementById('cap') as HTMLInputElement).value);
    this.indirizzo.civico=(document.getElementById('civico') as HTMLInputElement).value;
    this.indirizzo.provincia=(document.getElementById('provincia') as HTMLInputElement).value;
    this.bodyUpdateUtente.indirizzo=this.indirizzo;
    this.bodyUpdateUtente.ruolo=this.utente.ruolo.name;
    this.utenteService.updateUtente(this.bodyUpdateUtente).subscribe(element => {
      this.utente=element;
    });
    this.modalService.dismissAll();
  }

  controlloInputUpdate() {
    this.resultControllo = false;
    if ((document.getElementById('nome') as HTMLInputElement).value == '') {
      document.getElementById('ControlloNome').style.color = '#FF0000';
      document.getElementById('ControlloNome').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloNome').setAttribute('hidden', '');
    }
    if ((document.getElementById('email') as HTMLInputElement).value == '') {
      document.getElementById('ControlloEmail').style.color = '#FF0000';
      document.getElementById('ControlloEmail').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloEmail').setAttribute('hidden', '');
    }
    if ((document.getElementById('via') as HTMLInputElement).value == '') {
      document.getElementById('ControlloVia').style.color = '#FF0000';
      document.getElementById('ControlloVia').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloVia').setAttribute('hidden', '');
    }
    if ((document.getElementById('civico') as HTMLInputElement).value == '') {
      document.getElementById('ControlloCivico').style.color = '#FF0000';
      document.getElementById('ControlloCivico').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloCivico').setAttribute('hidden', '');
    }
    if ((document.getElementById('provincia') as HTMLInputElement).value == '') {
      document.getElementById('ControlloProvincia').style.color = '#FF0000';
      document.getElementById('ControlloProvincia').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloProvincia').setAttribute('hidden', '');
    }
    if ((document.getElementById('cap') as HTMLInputElement).value == '') {
      document.getElementById('ControlloCap').style.color = '#FF0000';
      document.getElementById('ControlloCap').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloCap').setAttribute('hidden', '');
    }
    return this.resultControllo;
  }

  aggiornaPassword() {
    if (this.controlloPassword()) {
      return;
    }
    // if(this.utenteService.verificaPassword()){
    //   return;
    // }
    var nuovaPassword=(document.getElementById('nuovaPassword') as HTMLInputElement).value;
    this.utentePassword.id=this.utente.id;
    this.utentePassword.password=nuovaPassword;

    this.utenteService.updateUtente(this.utentePassword).subscribe(element => {
      this.utente=element;
    });
    this.modalService.dismissAll();

  }
  private controlloPassword() {
    this.resultControllo = false;
    if ((document.getElementById('vecchiaPassword') as HTMLInputElement).value == '') {
      document.getElementById('ControlloVecchiaPassword').style.color = '#FF0000';
      document.getElementById('ControlloVecchiaPassword').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloVecchiaPassword').setAttribute('hidden', '');
    }
    if ((document.getElementById('nuovaPassword') as HTMLInputElement).value == '') {
      document.getElementById('ControlloNuovaPassword').style.color = '#FF0000';
      document.getElementById('ControlloNuovaPassword').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloNuovaPassword').setAttribute('hidden', '');
    }
    if ((document.getElementById('confermaPassword') as HTMLInputElement).value == '') {
      document.getElementById('ControlloConfermaPassword').style.color = '#FF0000';
      document.getElementById('ControlloConfermaPassword').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloConfermaPassword').setAttribute('hidden', '');
    }
    if ((document.getElementById('confermaPassword') as HTMLInputElement).value !=
        (document.getElementById('nuovaPassword') as HTMLInputElement).value) {
      document.getElementById('verificaPasswordUguali').style.color = '#FF0000';
      document.getElementById('verificaPasswordUguali').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('verificaPasswordUguali').setAttribute('hidden', '');
    }
    return this.resultControllo;
  }
}
