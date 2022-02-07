import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PiattoService } from '../../Theme/piatto/piatto.service';
import { getPiattoDto, Piatto } from '../../Theme/piatto/Piatto';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  allergeniInvio: string = '';
  tipologiaInvio: string = '';
  piattiDto: getPiattoDto = new getPiattoDto(this.tipologiaInvio, this.allergeniInvio);
  tipologie;
  tipologiePiatto;
  carrello;
  listaPiatti: Piatto[] = [];
  token = false;

  allergeni = {
    glutine: false,
    crostacei: false,
    uova: false,
    pesceCrudo: false,
    pesceCotto: false
  };

  formatter = new Intl.NumberFormat(undefined, {
    style: 'currency',
    currency: 'EUR'
  });

  constructor(private piattoService: PiattoService, private router: Router) {
    if (sessionStorage.getItem("token") != null) {
      this.token = true;
    }

    this.piattoService.getPiatti().subscribe((response: any) => {
      this.listaPiatti = [];
      response.forEach(element => {
        this.listaPiatti.push(element);
        this.listaPiatti = [...this.listaPiatti];
      });
    });

    this.getTipologie();

    if (sessionStorage.getItem("carrello") == null) {
      this.carrello = JSON.parse('{"carrello": []}');
    }
    else {
      this.carrello = JSON.parse(sessionStorage.getItem("carrello"));
    }
  }


  ngOnInit() {
    this.tipologie = 1;
  }


  parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
  };


  getPiattiLista() {
    return this.piattoService.getPiatti().subscribe((response: any) => {
      this.listaPiatti = [];
      response.forEach(element => {
        this.listaPiatti.push(element);
      });
    });
  }


  filtroTipologie(s: string) {
    this.tipologiaInvio = s;
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


    this.allergeniInvio = this.allergeniInvio.trimLeft();
    this.piattiDto.setAllergeni(this.allergeniInvio);
    console.log("Tipologia: " + this.piattiDto.tipologia + " AllergeniDto: " + this.piattiDto.allergeni)
    this.piattiFiltrati();
    console.log("AllergeniInvio: " + this.allergeniInvio);
  }


  getTipologie() {
    this.piattoService.getTipologie().subscribe(data => {
      this.tipologiePiatto = data.tipologie;
    });
  }


  aggiungiCarrello(numero: number, nome: string, prezzo: number) {
    let quant = Number((<HTMLInputElement>document.getElementById("quantita" + numero)).value);

    for(let i = 0; i < this.carrello.carrello.length; i++){
      if (this.carrello.carrello[i].numeretto == numero){
        this.carrello.carrello[i].quantita += quant;
        this.carrello.carrello[i].prezzo = prezzo * this.carrello.carrello[i].quantita;
        let json = '{"carrello": ' + JSON.stringify(this.carrello.carrello) + '}';
        sessionStorage.setItem("carrello", json);
        return;
      }
    }

    let piatto = JSON.parse('{"numeretto": ' + numero + ', "quantita": ' + quant + ', "nome": "' + nome + '", "prezzo": ' + prezzo * quant + '}');
    this.carrello.carrello.push(piatto);
    let json = '{"carrello": ' + JSON.stringify(this.carrello.carrello) + '}';

    sessionStorage.setItem("carrello", json);
  }
}
