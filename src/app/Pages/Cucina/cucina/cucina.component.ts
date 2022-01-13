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
  tavolo;

  constructor(private router: Router, private ordinazioneService: OrdinazioneService, private piattoservice: PiattoService, private modalService: NgbModal) {

  }

  infoOrdini() {
    this.ordinazioneService.getNonPagato().subscribe(data => {
      if (data.Status == 'Tutti i tavoli hanno già pagato.') {
        //TODO gestione errore
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
  }

  infoTavolo(n: number) {
    this.ordinazioneService.getInfoTavolo(n).subscribe(data => {
      if (data.Status == 'Tavolo libero.' || data.Status == 'Tavolo non trovato.') {
        //TODO gestire l'errore
      } else {
        this.piattiOrdinati = data.piattiOrdinati;
        document.getElementById('tavoloOrdinazione').innerHTML = "Ordinazione del tavolo " + data.tavolo + ":";
        document.getElementById('bottonePronto').removeAttribute('hidden');
        this.tavolo = data.tavolo;
      }
    }, error => {
      //TODO boh, da gestire o togliere, idk
    });
  }

  consegnato(){
    this.ordinazioneService.consegnato(this.tavolo).subscribe(data => {
      this.infoOrdini();
    });
  }

  ngOnInit() {
    this.infoOrdini();
    document.getElementById('bottonePronto').setAttribute('hidden', '');
    this.tavolo = 0;
  }

}
