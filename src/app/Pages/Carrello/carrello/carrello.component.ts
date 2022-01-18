import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OrdinazioneService } from 'src/app/Theme/Ordinazione/ordinazione.service';
import { PiattoService } from 'src/app/Theme/piatto/piatto.service';

@Component({
  selector: 'app-carrello',
  templateUrl: './carrello.component.html',
  styleUrls: ['./carrello.component.scss']
})
export class CarrelloComponent implements OnInit {

  piattiOrdinati;

  constructor(private router: Router, private ordinazioneService: OrdinazioneService, private piattoservice: PiattoService, private modalService: NgbModal) {
    this.piattiOrdinati = sessionStorage.getItem("carrello");

    alert(this.piattiOrdinati);
  }

  ngOnInit() {
  }

}
