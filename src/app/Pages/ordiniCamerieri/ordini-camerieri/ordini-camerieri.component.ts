import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {OrdinazioneService} from '../../../Theme/Ordinazione/ordinazione.service';
import {Ordinazione, OrdinazioneInvio} from '../../../Theme/Ordinazione/Ordinazione';
import {PiattoService} from '../../../Theme/piatto/piatto.service';
import {PiattoInvio} from '../../../Theme/piatto/Piatto';

@Component({
    selector: 'app-ordini-camerieri',
    templateUrl: './ordini-camerieri.component.html',
    styleUrls: ['./ordini-camerieri.component.scss']
})
export class OrdiniCamerieriComponent implements OnInit {


    Ordinazione: Ordinazione = new Ordinazione();
    jsonRicevuto;
    numeroTavolo: number = 0;
    display: boolean = false;
    errore: string;
    numeretto: number;
    quantita: number;
    //piattoOrdinati: string[] = [];

    piattiOrdinati: PiattoInvio[]=[];
    persone: number;
    tipologia: string;
    OrdinazioneAdd: OrdinazioneInvio= new OrdinazioneInvio();
    constructor(private router: Router, private ordinazioneService: OrdinazioneService, private piattoservice: PiattoService
    ) {
    }

    ngOnInit() {
    }

    //status: boolean=false;
    InfoTavolo(n: number) {
        this.ordinazioneService.getInfoTavolo(n).subscribe(data => {
            this.jsonRicevuto = data;
            console.log('Status: ' + this.jsonRicevuto.Status);
            console.log('Persone: ' + this.jsonRicevuto['persone']);
            if (this.jsonRicevuto.Status == 'Tavolo libero.') {
                document.getElementById('statusTavolo').innerHTML = 'Tavolo libero!';
                document.getElementById('statusTavolo').style.color = '#009933';
                document.getElementById('statusTavolo').removeAttribute('hidden');
                document.getElementById('persone').setAttribute('value', '');
                document.getElementById('tipologia').setAttribute('value', '');
            } else {
                this.persone=this.jsonRicevuto['persone'];
                this.tipologia=this.jsonRicevuto['tipologia'];
                document.getElementById('statusTavolo').setAttribute('hidden', '');
                document.getElementById('persone').setAttribute('value', this.jsonRicevuto['persone']);
                document.getElementById('tipologia').setAttribute('value', this.jsonRicevuto['tipologia']);

            }
        }, error => {
            document.getElementById('statusTavolo').innerHTML = 'Tavolo non esistente!';
            document.getElementById('statusTavolo').style.color = 'Red';
            document.getElementById('persone').setAttribute('value', '');
            document.getElementById('tipologia').setAttribute('value', '');
            document.getElementById('statusTavolo').removeAttribute('hidden');
        });
    }

    inputTavolo($event: any) {
        this.numeroTavolo = parseInt((event.target as HTMLInputElement).value);
        console.log('Input ' + this.numeroTavolo);
    }

    AggiungiPiatto() {
        this.numeretto = parseInt((document.getElementById('numeretto') as HTMLInputElement).value);
        this.quantita = parseInt((document.getElementById('quantita') as HTMLInputElement).value);
        //piattoEsiste()
        if (true) {
            //this.piattoOrdinati.push('{ "numeretto": ' + this.numeretto + ', "quantita": ' + this.quantita + '}');

            this.piattiOrdinati.push(new PiattoInvio(this.numeretto,this.quantita));
            console.log(this.piattiOrdinati[0]);
        }
        document.getElementById('numeretto').setAttribute('value', '');
        document.getElementById('quantita').setAttribute('value', '');
    }

    piattoEsiste() {
        return this.piattoservice.getEsiste(this.numeretto);
    }

    invioOrdinazione() {


        this.OrdinazioneAdd.tavolo=this.numeroTavolo;
        this.OrdinazioneAdd.tipologia=this.tipologia;
        this.OrdinazioneAdd.pagato=false;
        this.OrdinazioneAdd.persone=this.persone;
        this.OrdinazioneAdd.piattiOrdinati=this.piattiOrdinati;
        /**for (let i = 0; i < this.piattoOrdinati.length; i++) {
            this.OrdinazioneAdd.piattiOrdinati.push(this.piattoOrdinati[i].toString());
        }
         */

            this.ordinazioneService.aggiungiOrdinazione(this.OrdinazioneAdd).subscribe((response: any) => {
            });
            this.piattiOrdinati.splice(0);
            document.getElementById('numeretto').setAttribute('value', '');
            document.getElementById('quantita').setAttribute('value', '');

    }
}
