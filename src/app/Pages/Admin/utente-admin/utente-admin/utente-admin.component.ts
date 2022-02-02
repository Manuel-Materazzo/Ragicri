import { Component, OnInit } from '@angular/core';
import {UtenteService} from '../../../../Theme/Utente/utente.service';
import {Router} from '@angular/router';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AddUtenteDTO, UpdateUtenteDTO, Utente} from '../../../../Theme/Utente/Utente';
import {element} from 'protractor';
import {Piatto} from '../../../../Theme/piatto/Piatto';
import {IndirizzoService} from '../../../../Theme/Indirizzo/indirizzo.service';
import {Role} from '../../../../Theme/Role/role';
import {RoleService} from '../../../../Theme/Role/role.service';

@Component({
  selector: 'app-utente-admin',
  templateUrl: './utente-admin.component.html',
  styleUrls: ['./utente-admin.component.scss']
})
export class UtenteAdminComponent implements OnInit {

  listaUtenti: Utente[];
  listaRuoli: Role[];
  closeResult: string;
  utente: AddUtenteDTO= new AddUtenteDTO();
  idUtente: number;
  resultControllo: boolean;
  bodyUpdateUtente: UpdateUtenteDTO= new UpdateUtenteDTO();
  constructor(private utenteService: UtenteService,private roleService: RoleService,
              private router: Router, private modalService: NgbModal) { }

  ngOnInit() {

    this.utenteService.getAllUtentiAzienda().subscribe((response: any)=>{
      this.listaUtenti=[];
      response.forEach(element=>{
        this.listaUtenti.push(element);
        this.ordinaUtenti();
      })
    })

    this.roleService.getAllRuoli().subscribe((response: any)=>{
      this.listaRuoli=[];
      response.forEach(element=>{
        this.listaRuoli.push(element);
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

  //funzione per il model modifica
  openUpdateModal(content, id: number) {
    this.utenteService.getById(id).subscribe(async (response: any) => {
      this.modalService.open(content, {
        size: 'lg'
      });
      this.bodyUpdateUtente = response;
      (document.getElementById('updateNome') as HTMLInputElement).value = this.bodyUpdateUtente.nome;
      (document.getElementById('updateEmail') as HTMLInputElement).value = this.bodyUpdateUtente.email;
      (document.getElementById('updateUsername') as HTMLInputElement).value = this.bodyUpdateUtente.username;
      await this.sleep(200);
    });
  }

  sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
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

    if (this.controlloInputCreate()) {
      return;
    }

    this.utente.nome=(document.getElementById('addNome') as HTMLInputElement).value;
    this.utente.email=(document.getElementById('addEmail') as HTMLInputElement).value;
    this.utente.username=(document.getElementById('addUsername') as HTMLInputElement).value;
    this.utente.password=(document.getElementById('addPassword') as HTMLInputElement).value;
    this.utente.ruolo=(document.getElementById('addRuolo') as HTMLInputElement).value;
    this.utente.IndirizzoDTO=null;
    this.utenteService.registraUtente(this.utente).subscribe((response: any) => {
      this.listaUtenti.push(response);
      this.ordinaUtenti();
    });
    this.modalService.dismissAll();
  }

  controlloInputCreate() {
    this.resultControllo = false;
    if ((document.getElementById('addNome') as HTMLInputElement).value == '') {
      document.getElementById('ControlloNome').style.color = '#FF0000';
      document.getElementById('ControlloNome').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloNome').setAttribute('hidden', '');
    }
    if ((document.getElementById('addUsername') as HTMLInputElement).value == '') {
      document.getElementById('ControlloUsername').style.color = '#FF0000';
      document.getElementById('ControlloUsername').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloUsername').setAttribute('hidden', '');
    }
    if ((document.getElementById('addPassword') as HTMLInputElement).value == '') {
      document.getElementById('ControlloPassword').style.color = '#FF0000';
      document.getElementById('ControlloPassword').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloPassword').setAttribute('hidden', '');
    }
    if ((document.getElementById('addEmail') as HTMLInputElement).value == '') {
      document.getElementById('ControlloEmail').style.color = '#FF0000';
      document.getElementById('ControlloEmail').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloEmail').setAttribute('hidden', '');
    }
    return this.resultControllo;
  }

  updateUtente() {
    if (this.controlloInputUpdate()) {
      return;
    }
    this.bodyUpdateUtente.nome=(document.getElementById('updateNome') as HTMLInputElement).value;
    this.bodyUpdateUtente.email=(document.getElementById('updateEmail') as HTMLInputElement).value;
    this.bodyUpdateUtente.username=(document.getElementById('updateUsername') as HTMLInputElement).value;
    this.bodyUpdateUtente.password=(document.getElementById('updatePassword') as HTMLInputElement).value;
    this.bodyUpdateUtente.ruolo=(document.getElementById('updateRuolo') as HTMLInputElement).value;
    this.bodyUpdateUtente.indirizzo=null;

    this.utenteService.updateUtente(this.bodyUpdateUtente).subscribe(element => {
      for (var index = 0; index < this.listaUtenti.length; index++) {
        if (this.listaUtenti[index].id == element.id) {
          this.listaUtenti[index] = element;
          this.listaUtenti = [...this.listaUtenti];
        }
      }
    });
    this.modalService.dismissAll();
  }


  controlloInputUpdate() {
    this.resultControllo = false;
    if ((document.getElementById('updateNome') as HTMLInputElement).value == '') {
      document.getElementById('ControlloNomeUpdate').style.color = '#FF0000';
      document.getElementById('ControlloNomeUpdate').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloNomeUpdate').setAttribute('hidden', '');
    }
    if ((document.getElementById('updateUsername') as HTMLInputElement).value == '') {
      document.getElementById('ControlloUsernameUpdate').style.color = '#FF0000';
      document.getElementById('ControlloUsernameUpdate').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloUsernameUpdate').setAttribute('hidden', '');
    }
    if ((document.getElementById('updatePassword') as HTMLInputElement).value == '') {
      document.getElementById('ControlloPasswordUpdate').style.color = '#FF0000';
      document.getElementById('ControlloPasswordUpdate').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloPasswordUpdate').setAttribute('hidden', '');
    }
    if ((document.getElementById('updateEmail') as HTMLInputElement).value == '') {
      document.getElementById('ControlloEmailUpdate').style.color = '#FF0000';
      document.getElementById('ControlloEmailUpdate').removeAttribute('hidden');
      this.resultControllo = true;
    } else {
      document.getElementById('ControlloEmailUpdate').setAttribute('hidden', '');
    }
    return this.resultControllo;
  }
}
