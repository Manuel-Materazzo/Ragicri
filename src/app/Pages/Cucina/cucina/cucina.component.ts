import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrdinazioneService } from '../../../Theme/Ordinazione/ordinazione.service';
import { Ordinazione, OrdinazioneInvio } from '../../../Theme/Ordinazione/Ordinazione';
import { PiattoService } from '../../../Theme/piatto/piatto.service';
import { PiattoInvio } from '../../../Theme/piatto/Piatto';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-cucina',
  templateUrl: './cucina.component.html',
  styleUrls: ['./cucina.component.scss']
})
export class CucinaComponent implements OnInit {

  piattiOrdinati;
  ordinazioni;
  ordinazioniAsporto;
  tavolo;
  ordinazioneId;

  constructor(private router: Router, private ordinazioneService: OrdinazioneService, private piattoservice: PiattoService, private modalService: NgbModal) {
    if (sessionStorage.getItem("token") == null) {
      window.location.replace("/");
    }
    if (this.parseJwt(sessionStorage.getItem("token")).roles.id == 3) {
      window.location.replace("/");
    }
  }

  ngOnInit() {
    this.infoOrdini();
    document.getElementById('bottonePronto').setAttribute('hidden', '');
    document.getElementById('bottoneProntoAsporto').setAttribute('hidden', '');
    document.getElementById('ordinazioniNonPresenti').setAttribute('hidden', '');
    document.getElementById('ordinazioniAsportoNonPresenti').setAttribute('hidden', '');
    this.tavolo = 0;
    this.ordinazioneId = 0;
  }

  parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
  };


  infoOrdini() {
    this.ordinazioneService.getNonPagato().subscribe(data => {
      if (data.status == 'Tutti i tavoli hanno già pagato.') {
        document.getElementById("ordinazioniNonPresenti").removeAttribute("hidden");
        this.ordinazioni = null;
      }
      else {
        let cont = 0;
        for (let i = 0; i < data.length; i++) {
          for (let y = 0; y < data[i].piattiOrdinati.length; y++) {
            if (data[i].piattiOrdinati[y].consegnato == true) {
              cont++;
            }
          }
          if (cont == data[i].piattiOrdinati.length) {
            data[i].preparato = "Sì";
          }
          else {
            data[i].preparato = "No";
          }
          cont = 0;
        }

        this.ordinazioni = data;
      }
    })

    this.ordinazioneService.getAsportoDomicilioNonConsegnato().subscribe(data => {
      if (data.status == 'Non ci sono ordinazioni.') {
        document.getElementById("ordinazioniAsportoNonPresenti").removeAttribute("hidden");
        this.ordinazioniAsporto = null;
      }
      else {
        let cont = 0;
        for (let i = 0; i < data.length; i++) {
          for (let y = 0; y < data[i].piattiOrdinati.length; y++) {
            if (data[i].piattiOrdinati[y].consegnato == true) {
              cont++;
            }
          }
          if (cont == data[i].piattiOrdinati.length) {
            data[i].preparato = "Sì";
          }
          else {
            data[i].preparato = "No";
          }
          cont = 0;
        }

        this.ordinazioniAsporto = data;
      }
    });
  }

  infoTavolo(n: number) {
    this.ordinazioneService.getInfoTavolo(n).subscribe(data => {
      if (data.Status == 'Tavolo libero.' || data.Status == 'Tavolo non trovato.') {
        //niente
      } else {
        document.getElementById('bottoneProntoAsporto').setAttribute('hidden', '');
        this.piattiOrdinati = data.piattiOrdinati;
        document.getElementById('tavoloOrdinazione').innerHTML = "Ordinazione del tavolo " + data.tavolo + ":";
        document.getElementById('bottonePronto').removeAttribute('hidden');
        this.tavolo = data.tavolo;
      }
    });
  }

  infoOrdinazione(id: number) {
    this.ordinazioneService.getInfoOrd(id).subscribe(data => {
      if (data.Status == 'Ordinazione non trovata.') {
        //niente
      } else {
        document.getElementById('bottonePronto').setAttribute('hidden', '');
        this.piattiOrdinati = data.piattiOrdinati;
        document.getElementById('tavoloOrdinazione').innerHTML = "Ordinazione " + data.tipologia + " " + data.idOrdinazione + ":";
        document.getElementById('bottoneProntoAsporto').removeAttribute('hidden');
        this.ordinazioneId = data.idOrdinazione;
      }
    });
  }

  consegnato() {
    this.ordinazioneService.consegnato(this.tavolo).subscribe(data => {
      this.infoOrdini();
    });
  }

  consegnatoAsporto() {
    this.ordinazioneService.consegnatoId(this.ordinazioneId).subscribe(data => {
      this.infoOrdini();
    });
  }

}
