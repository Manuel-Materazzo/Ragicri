import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OrdinazioneService } from 'src/app/Theme/Ordinazione/ordinazione.service';
import { PiattoService } from 'src/app/Theme/piatto/piatto.service';
import { UtenteService } from 'src/app/Theme/Utente/utente.service';

@Component({
  selector: 'app-carrello',
  templateUrl: './carrello.component.html',
  styleUrls: ['./carrello.component.scss']
})
export class CarrelloComponent implements OnInit {

  carrello;
  email: string;

  constructor(private router: Router, private ordinazioneService: OrdinazioneService, private piattoservice: PiattoService, private utenteService: UtenteService, private modalService: NgbModal) {

  }

  ngOnInit() {
    if (sessionStorage.getItem("carrello") == null){
      document.getElementById("contenutoPrincipale").setAttribute('hidden', '');
    }
    else {
      this.carrello = JSON.parse(sessionStorage.getItem("carrello")).carrello;
      document.getElementById("carrelloVuoto").setAttribute('hidden', '');
    }
  }

  elimina(numeretto: number) {

    for (let i = 0; i < this.carrello.length; i++) {
      if (this.carrello[i].numeretto == numeretto){
        this.carrello.splice(i, 1);

        let json = '{"carrello": ' + JSON.stringify(this.carrello) + '}';
        sessionStorage.setItem("carrello", json);
      }
    }
  }

  removeEmptyOrNull = (obj) => { 

    Object.keys(obj).forEach(k => 

      (obj[k] && typeof obj[k] === 'object') && this.removeEmptyOrNull(obj[k]) || 

      (!obj[k] && obj[k] !== undefined) && delete obj[k] 

    ); 

    return obj; 

  };

  annulla(){
    this.modalService.dismissAll();
  }

  conferma(){
    this.modalService.dismissAll();
    sessionStorage.removeItem("carrello");
    let tipologia = (<HTMLInputElement>document.getElementById("modalita")).value;
    let orario = String((<HTMLInputElement>document.getElementById("orario")).value);
    this.utenteService.getIdIndirizzoByUsername(sessionStorage.getItem("username")).subscribe(data => {
      let json = '{"pagato": true, "persone": 0, "piattiOrdinati": ' + JSON.stringify(this.carrello) + ', "tavolo": 0, "tipologia": "' + tipologia + '",' +
      '"idIndirizzo": ' + data.numero + ', "orarioConsegna": "' + orario + '"}';
      this.ordinazioneService.inviaMail(sessionStorage.getItem("email")).subscribe();
      this.ordinazioneService.aggiungiOrdinazioneIndirizzo(JSON.parse(json)).subscribe(data2 => {
        window.location.reload();
      });
    });
  }

  pagamento(contentTrue, contentFalse) {
    if((<HTMLInputElement>document.getElementById("orario")).value == null || (<HTMLInputElement>document.getElementById("orario")).value == undefined
    || (<HTMLInputElement>document.getElementById("orario")).value == ""){
      console.log("entrato");
      this.modalService.open(contentFalse, {
        size: 'sm'
      });
    }
    else {
      this.modalService.open(contentTrue, {
        size: 'lg'
      });
    }
  }

}
