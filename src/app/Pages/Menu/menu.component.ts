import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {PiattoService} from '../../Theme/piatto/piatto.service';
import {getPiattoDto, Piatto} from '../../Theme/piatto/Piatto';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

    allergeniInvio: string = '';
    tipologiaInvio: string='';
    piattiDto: getPiattoDto = new getPiattoDto(this.tipologiaInvio, this.allergeniInvio);
    tipologie;
    tipologiePiatto;

    allergeni = {
        glutine: false,
        crostacei: false,
        uova: false,
        pesceCrudo: false,
        pesceCotto: false
    };

    constructor(
        private piattoService: PiattoService,
        private router: Router
    ) {
        this.piattoService.getPiatti().subscribe((response: any) => {
            this.listaPiatti = [];
            response.forEach(element => {
                this.listaPiatti.push(element);
                this.listaPiatti = [...this.listaPiatti];
            });
        });
        this.getTipologie();
    }

    listaPiatti: Piatto[] = [];

    ngOnInit() {
    }

    getPiattiLista() {
        return this.piattoService.getPiatti().subscribe((response: any) => {
            this.listaPiatti = [];
            response.forEach(element => {
                this.listaPiatti.push(element);
            });
        });
    }

    filtroTipologie(s: string) {
        //salvare tipologia variabile globali
        this.tipologiaInvio=s;
        this.piattiDto.setTipologia(this.tipologiaInvio);
      this.piattiFiltrati();
    }

    piattiFiltrati() {
        this.piattoService.getPiattiFiltrati(this.piattiDto).subscribe((response: any) => {
            this.listaPiatti = [];
            response.forEach(element => {
                this.listaPiatti.push(element);
            });
        });
    }

    test(){
        console.log();
    }

    filtroAllergeni(allergene: string) {
        if (this.allergeniInvio.includes(allergene)) {
            let parts = this.allergeniInvio.split(' ');

            this.allergeniInvio = '';

            for (let i = 0; i < parts.length; i++) {
                if (parts[i] != allergene && i == 0) {
                    this.allergeniInvio += parts[i];
                } else if (parts[i] != allergene) {
                    this.allergeniInvio += ' ' + parts[i];
                }
            }
        } else {
            this.allergeniInvio += allergene + ' ';
        }

        // @ts-ignore
        this.allergeniInvio=this.allergeniInvio.trimLeft();
        this.piattiDto.setAllergeni(this.allergeniInvio);
        console.log("Tipologia: "+this.piattiDto.tipologia+ " AllergeniDto: "+this.piattiDto.allergeni)
        this.piattiFiltrati();
        console.log("AllergeniInvio: "+this.allergeniInvio);

        //this.piattiDto.setAllergeni(this.piattiDto.getAllergeni() + ' ' + allergene);
        /*
        let invio = {
          "allergeni": this.allergeniInvio,
          "tipologia": ""
        }
         */
    }

    getTipologie(){
        this.piattoService.getTipologie().subscribe(data => {
            this.tipologiePiatto = data.tipologie;
        });
    }


}
