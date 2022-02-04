import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OrdinazioneService } from 'src/app/Theme/Ordinazione/ordinazione.service';
import { PiattoService } from 'src/app/Theme/piatto/piatto.service';

@Component({
  selector: 'app-cassa',
  templateUrl: './cassa.component.html',
  styleUrls: ['./cassa.component.scss']
})
export class CassaComponent implements OnInit {

  ordinazioni;
  piattiOrdinati;
  tavolo;

  constructor(private router: Router, private ordinazioneService: OrdinazioneService, private piattoservice: PiattoService, private modalService: NgbModal) {
    if (sessionStorage.getItem("token") == null) {
      window.location.replace("/");
    }
    else if (this.parseJwt(sessionStorage.getItem("token")).roles.id == 3) {
      window.location.replace("/");
    }
  }

  ngOnInit() {
    this.infoOrdini();
    document.getElementById('bottonePronto').setAttribute('hidden', '');
    document.getElementById('ordinazioniNonPresenti').setAttribute('hidden', '');
    this.tavolo = 0;
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
  }

  infoTavolo(n: number) {
    this.ordinazioneService.getInfoTavolo(n).subscribe(data => {
      if (data.Status == 'Tavolo libero.' || data.Status == 'Tavolo non trovato.') {
        //niente
      } else {
        this.piattiOrdinati = data.piattiOrdinati;
        document.getElementById('tavoloOrdinazione').innerHTML = "Ordinazione del tavolo " + data.tavolo + ":";
        document.getElementById('bottonePronto').removeAttribute('hidden');
        this.tavolo = data.tavolo;
      }
    });
    console.log(this.tavolo);
  }

  pagato() {
    this.ordinazioneService.setPagato(this.tavolo).subscribe(data => {
      this.infoOrdini();
      this.piattiOrdinati = null;
      document.getElementById('tavoloOrdinazione').innerHTML = "Seleziona un'ordine.";
      document.getElementById('bottonePronto').setAttribute('hidden', '');
    });
  }

}
