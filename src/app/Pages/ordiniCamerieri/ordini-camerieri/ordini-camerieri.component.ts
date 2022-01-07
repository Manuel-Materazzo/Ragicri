import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {OrdinazioneService} from '../../../Theme/Ordinazione/ordinazione.service';
import {Ordinazione} from '../../../Theme/Ordinazione/Ordinazione';
import {PiattoOrdinato} from '../../../Theme/piattoOrdinato/PiattoOrdinato';

@Component({
  selector: 'app-ordini-camerieri',
  templateUrl: './ordini-camerieri.component.html',
  styleUrls: ['./ordini-camerieri.component.scss']
})
export class OrdiniCamerieriComponent implements OnInit {

  constructor(private router: Router,private ordinazioneService: OrdinazioneService) { }

  ngOnInit() {
  }
Ordinazione: Ordinazione= new Ordinazione();
  numeroTavolo:number=0;
  display: boolean = false;
  errore: string;
  piattiDaOrdinare: PiattoOrdinato[];
  InfoTavolo(n: number){
    this.ordinazioneService.getInfoTavolo(n).subscribe(data => {

      this.Ordinazione=data;
    })

  }

  inputTavolo($event: any) {
    this.numeroTavolo = parseInt((event.target as HTMLInputElement).value);
    console.log("Input " +this.numeroTavolo)
  }
}
