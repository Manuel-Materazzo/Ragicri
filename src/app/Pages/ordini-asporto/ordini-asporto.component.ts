import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OrdinazioneService } from 'src/app/Theme/Ordinazione/ordinazione.service';
import { PiattoService } from 'src/app/Theme/piatto/piatto.service';

@Component({
  selector: 'app-ordini-asporto',
  templateUrl: './ordini-asporto.component.html',
  styleUrls: ['./ordini-asporto.component.scss']
})
export class OrdiniAsportoComponent implements OnInit {

  ordinazioni;
  piattiOrdinati;
  ordinazioneId;
  tipologia;
  orario;
  indirizzo;
  idOrdinazione;
  preparato;
  noOrdinazioni;

  constructor(private router: Router, private ordinazioneService: OrdinazioneService, private piattoservice: PiattoService, private modalService: NgbModal) {
    if (sessionStorage.getItem("token") == null) {
      window.location.replace("/");
    }
    if (this.parseJwt(sessionStorage.getItem("token")).roles.id == 3) {
      window.location.replace("/");
    }
  }

  ngOnInit() {
    this.getOrdinazioni();
    document.getElementById("divInfo").setAttribute("hidden", "");
    document.getElementById("bottoneConsegnato").setAttribute("hidden", "");
    document.getElementById("ordinazioniNonPresenti").setAttribute("hidden", "");
  }

  parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
  };

  getOrdinazioni() {
    this.ordinazioneService.getAsportoDomicilioNonConsegnato().subscribe(data => {
      if (data.status == 'Non ci sono ordinazioni.') {
        document.getElementById("ordinazioniNonPresenti").removeAttribute("hidden");
        console.log("HELOOO, SONO ENTRATOOO");
        this.ordinazioni = null;
      }
      else {
        let cont = 0;
        for (let i = 0; i < data.length; i++) {
          for(let y = 0; y < data[i].piattiOrdinati.length; y++){
            if(data[i].piattiOrdinati[y].consegnato == true){
              cont++;
            }
          }
          if(cont == data[i].piattiOrdinati.length){
            data[i].preparato = "Sì";
          }
          else {
            data[i].preparato = "No";
          }
          cont = 0;
        }

        this.ordinazioni = data;
      }
    });
  }

  infoOrdinazione(id: number) {
    this.ordinazioneService.getInfoOrd(id).subscribe(data => {
      if (data.Status == 'Ordinazione non trovata.') {
        //TODO
      } else {
        document.getElementById("ordinazioniNonPresenti").setAttribute("hidden", "");
        this.piattiOrdinati = data.piattiOrdinati;
        document.getElementById('tavoloOrdinazione').innerHTML = "Ordinazione " + data.tipologia + " " + data.idOrdinazione + ":";
        this.ordinazioneId = data.idOrdinazione;
        this.orario = data.orarioConsegna;
        this.tipologia = data.tipologia;
        this.indirizzo = data.indirizzo.via + " " + data.indirizzo.civico + " (" + data.indirizzo.provincia + "), " + data.indirizzo.cap;
        this.idOrdinazione = data.idOrdinazione;
        if(data.preparato){
          this.preparato = "Sì";
        }
        else {
          this.preparato = "No";
        }
        document.getElementById("divInfo").removeAttribute("hidden");
        document.getElementById("bottoneConsegnato").removeAttribute("hidden");
      }
      this.getOrdinazioni();
    });
  }

  consegnato(){
    this.ordinazioneService.setConsegnato(this.idOrdinazione).subscribe(data => {
      this.getOrdinazioni();
      document.getElementById('tavoloOrdinazione').innerHTML = "Seleziona un'ordinazione.";
      document.getElementById("divInfo").setAttribute("hidden", "");
      document.getElementById("bottoneConsegnato").setAttribute("hidden", "");
    });
  }

}
