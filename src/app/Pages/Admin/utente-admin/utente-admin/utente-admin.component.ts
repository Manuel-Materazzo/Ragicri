import { Component, OnInit } from '@angular/core';
import {UtenteService} from '../../../../Theme/Utente/utente.service';
import {Router} from '@angular/router';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AddUtenteDTO, Utente} from '../../../../Theme/Utente/Utente';
import {element} from 'protractor';
import {Piatto} from '../../../../Theme/piatto/Piatto';
import {IndirizzoService} from '../../../../Theme/Indirizzo/indirizzo.service';

@Component({
  selector: 'app-utente-admin',
  templateUrl: './utente-admin.component.html',
  styleUrls: ['./utente-admin.component.scss']
})
export class UtenteAdminComponent implements OnInit {

  listaUtenti: Utente[];
  closeResult: string;
  utente: AddUtenteDTO= new AddUtenteDTO();
  idUtente: number;
  constructor(private utenteService: UtenteService,
              private router: Router, private modalService: NgbModal) { }

  ngOnInit() {

    this.utenteService.getAllUtentiAzienda().subscribe((response: any)=>{
      this.listaUtenti=[];
      response.forEach(element=>{
        this.listaUtenti.push(element);
        this.ordinaUtenti();
      })
    })
  }

  //Filtro ordine di visualizzazione nella tabella
   ordinaUtenti() {
     this.listaUtenti.sort((a: Utente, b: Utente) => {
       if (a.id > b.id) {
         return 1;
       }
       if (a.id < b.id) {
         return -1;
       }
       return 0;
     });
     this.listaUtenti = [...this.listaUtenti];
  }


  //funzione per il model
  open(content) {
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  //funzione per il model elimina
  openDeleteModal(content, idUtente: number, nomeUtente: string) {
    this.utente.nome=nomeUtente;
    this.modalService.open(content, {
      size: 'sm'
    });
    this.idUtente=idUtente;
  }

  openCreateModal(content) {
    this.modalService.open(content, {
      size: 'lg'
    });
  }

  //funzione per il model
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  deleteUtente(idUtente: number) {
    this.utenteService.eliminaUtente(idUtente).subscribe();
    for (var index = 0; index < this.listaUtenti.length; index++) {
      if (this.listaUtenti[index].id == this.idUtente) {
        this.listaUtenti.splice(index, 1);
        this.listaUtenti = [...this.listaUtenti];
      }
    }
    this.modalService.dismissAll();
  }

  createUtente() {

  }
}
