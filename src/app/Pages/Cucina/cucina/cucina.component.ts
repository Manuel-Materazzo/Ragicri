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

  ordinazione;
  piattiOrdinati;
  ordinazioni;
  ordinazioniAsporto;
  tavolo;
  ordinazioneId;

  constructor(private router: Router, private ordinazioneService: OrdinazioneService, private piattoservice: PiattoService, private modalService: NgbModal) {

  }

  infoOrdini() {
    this.ordinazioneService.getNonPagato().subscribe(data => {
      if (data.Status == 'Tutti i tavoli hanno già pagato.') {
        //niente
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
    })

    this.ordinazioneService.getAsportoDomicilio().subscribe(data => {
      if (data.Status == 'Non ci sono ordinazioni.') {
        //niente
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

  infoOrdinazione(id: number){
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

  consegnato(){
    this.ordinazioneService.consegnato(this.tavolo).subscribe(data => {
      this.infoOrdini();
    });
  }

  consegnatoAsporto(){
    this.ordinazioneService.consegnatoId(this.ordinazioneId).subscribe(data => {
      this.infoOrdini();
    });
  }

  ngOnInit() {
    this.infoOrdini();
    document.getElementById('bottonePronto').setAttribute('hidden', '');
    document.getElementById('bottoneProntoAsporto').setAttribute('hidden', '');
    this.tavolo = 0;
    this.ordinazioneId = 0;
  }

}
