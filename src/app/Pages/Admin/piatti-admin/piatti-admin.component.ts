import {Component, OnInit} from '@angular/core';
import {PiattoService} from '../../../Theme/piatto/piatto.service';
import {Router} from '@angular/router';
import {AddPiattoDTO, Piatto} from '../../../Theme/piatto/Piatto';
import {element} from 'protractor';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-piatti-admin',
    templateUrl: './piatti-admin.component.html',
    styleUrls: ['./piatti-admin.component.scss']
})
export class PiattiAdminComponent implements OnInit {

    listiPiattini: Piatto[] = [];
    piatto: AddPiattoDTO= new AddPiattoDTO();
    //variabile per il model
    closeResult: string;
    numerettoPiatto: number;
    indice: number;
    constructor(private piattoService: PiattoService,
                private router: Router, private modalService: NgbModal) {

    }

    ngOnInit() {
        this.piattoService.getPiatti().subscribe((response: any) => {
            this.listiPiattini = [];
            response.forEach(element => {
                this.listiPiattini.push(element);
                this.listiPiattini = [...this.listiPiattini];
            });

        });
    }

    //funzione per il model modfica
    openUpdateModal(content, numeretto: number) {
        this.piattoService.getPiatto(numeretto).subscribe((response: any) => {
            this.piatto=response;
            this.modalService.open(content, {
                size: 'lg'
            });
            (document.getElementById('nomePiatto') as HTMLInputElement).value = this.piatto.nome;
            (document.getElementById('prezzo') as HTMLInputElement).value = this.piatto.prezzo+"";
            (document.getElementById('numerettoPiatto') as HTMLInputElement).value = this.piatto.numero+"";
            (document.getElementById('allergeni') as HTMLInputElement).value = this.piatto.allergeni;
            //(document.getElementById('img') as HTMLInputElement).value = this.piatto.img;
            (document.getElementById('tipologia') as HTMLInputElement).value = this.piatto.tipologia;
        });
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
    openDeleteModal(content, numeretto: number) {
        this.modalService.open(content, {
            size: 'sm'
        });
        this.numerettoPiatto=numeretto;
    }

    openCreateModal(content){
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

    updatePiatto(){
        this.piatto.nome=(document.getElementById('nomePiatto') as HTMLInputElement).value;
        this.piatto.prezzo=parseInt((document.getElementById('prezzo') as HTMLInputElement).value);
        this.piatto.numero=parseInt((document.getElementById('numerettoPiatto') as HTMLInputElement).value);
        this.piatto.allergeni=(document.getElementById('allergeni') as HTMLInputElement).value;
        //(document.getElementById('img') as HTMLInputElement).value = this.piatto.img;
        this.piatto.tipologia=(document.getElementById('tipologia') as HTMLInputElement).value;
        this.piattoService.updatePiatto(this.piatto).subscribe();
        this.modalService.dismissAll();
        window.location.reload();
    }
    deletePiatto(numeretto: number) {
        this.piattoService.deletePiatto(numeretto).subscribe();
      for (var index = 0; index < this.listiPiattini.length; index++) {
        if(this.listiPiattini[index].numero==this.numerettoPiatto){
          this.listiPiattini.splice(index, 1);
          this.listiPiattini = [...this.listiPiattini];
        }
      }this.modalService.dismissAll();
    }

    createPiatto() {
        console.log(this.piatto.nome)
        this.piatto.nome=(document.getElementById('nomePiattoAdd') as HTMLInputElement).value;
        this.piatto.prezzo=parseInt((document.getElementById('prezzoAdd') as HTMLInputElement).value);
        this.piatto.numero=parseInt((document.getElementById('numerettoPiattoAdd') as HTMLInputElement).value);
        this.piatto.allergeni=(document.getElementById('allergeniAdd') as HTMLInputElement).value;
        //this.piatto.img=(document.getElementById('imgAdd') as HTMLInputElement).value;
        this.piatto.tipologia=(document.getElementById('tipologiaAdd') as HTMLInputElement).value;
        this.piattoService.addPiatto(this.piatto).subscribe();
        this.modalService.dismissAll();
        window.location.reload();
    }
}
