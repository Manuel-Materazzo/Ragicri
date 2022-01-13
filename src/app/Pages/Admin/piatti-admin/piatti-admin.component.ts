import {Component, OnInit} from '@angular/core';
import {PiattoService} from '../../../Theme/piatto/piatto.service';
import {Router} from '@angular/router';
import {Piatto} from '../../../Theme/piatto/Piatto';
import {element} from 'protractor';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-piatti-admin',
    templateUrl: './piatti-admin.component.html',
    styleUrls: ['./piatti-admin.component.scss']
})
export class PiattiAdminComponent implements OnInit {

    listiPiattini: Piatto[] = [];
    piatto: Piatto;
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

    deletePiatto(numeretto: number) {
        this.piattoService.deletePiatto(numeretto).subscribe(data => {});
      for (var index = 0; index < this.listiPiattini.length; index++) {
        if(this.listiPiattini[index].numero==this.numerettoPiatto){
          this.listiPiattini.splice(index, 1);
          this.listiPiattini = [...this.listiPiattini];
        }
      }this.modalService.dismissAll();
    }
}
