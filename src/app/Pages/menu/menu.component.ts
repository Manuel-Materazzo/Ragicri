import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {PiattoService} from '../../Theme/piatto/piatto.service';
import {Piatto} from '../../Theme/piatto/Piatto';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  tipologie = {
    menuCompleto: true,
    antipasti: false,
    primiPiatti: false,
    nigiri: false,
    hosomaki: false,
    temaki: false,
    sashimi: false,
    dessert: false,
    bevande: false
  };

  constructor(
      private piattoService: PiattoService,
      private router: Router
  ) {
    this.piattoService.getPiatti().subscribe((response:any)=>{
      this.listaPiatti=[];
      response.forEach(element=>{
        this.listaPiatti.push(element)
        this.listaPiatti=[...this.listaPiatti];
      });
    })
  }

  listaPiatti:Piatto[]=[];
  ngOnInit() {
  }


  getPiattiLista(){
    return this.piattoService.getPiatti().subscribe((response:any)=>{
      this.listaPiatti=[];
      response.forEach(element=>{
        this.listaPiatti.push(element)
      });
    })
  }

  filtroTipologie(s: string) {
     this.piattoService.getTipologiaPiatto(s).subscribe((response:any)=>{
      this.listaPiatti=[];
      response.forEach(element=>{
        this.listaPiatti.push(element)
      });
    })
  }


}
