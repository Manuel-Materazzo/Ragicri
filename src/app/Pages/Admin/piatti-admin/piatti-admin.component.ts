import {Component, OnInit} from '@angular/core';
import {PiattoService} from '../../../Theme/piatto/piatto.service';
import {Router} from '@angular/router';
import {AddPiattoDTO, Piatto, TipologieDTO} from '../../../Theme/piatto/Piatto';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';


@Component({
    selector: 'app-piatti-admin',
    templateUrl: './piatti-admin.component.html',
    styleUrls: ['./piatti-admin.component.scss']
})
export class PiattiAdminComponent implements OnInit {

    listiPiattini: Piatto[] = [];
    piatto: AddPiattoDTO = new AddPiattoDTO();
    //variabile per il model
    closeResult: string;
    numerettoPiatto: number;
    indice: number;
    immaginePiatto: File = null;
    url: string | ArrayBuffer = '';
    allergeni: string = '';
    tipologie: TipologieDTO[];
    resultControllo: boolean;
    allergeniSingoloPiatto: string = '';
    nomeImmagine: string="";

    constructor(private piattoService: PiattoService,
                private router: Router, private modalService: NgbModal) {

    }


    ngOnInit() {
        this.piattoService.getPiatti().subscribe((response: any) => {
            this.listiPiattini = [];
            response.forEach(element => {
                this.listiPiattini.push(element);
                this.ordinaLista();
            });
        });
        this.piattoService.getTipologie().subscribe((response: any) => {
            this.tipologie = response;
        });
    }

    //funzione per il model modfica
    openUpdateModal(content, numeretto: number) {
        this.piattoService.getPiatto(numeretto).subscribe((response: any) => {
            this.piatto = response;
            this.allergeniSingoloPiatto = this.piatto.allergeni;
            this.modalService.open(content, {
                size: 'lg'
            });
            this.riempimentoAllergeniUpdate();
            (document.getElementById('updateNomePiatto') as HTMLInputElement).value = this.piatto.nome;
            (document.getElementById('updatePrezzo') as HTMLInputElement).value = this.piatto.prezzo + '';
            //(document.getElementById('img') as HTMLInputElement).value = this.piatto.img;
            (document.getElementById('updateTipologia') as HTMLInputElement).value = this.piatto.tipologia;
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
    openDeleteModal(content, numeretto: number, nomePiatto: string) {
        this.piatto.nome = nomePiatto.toLocaleLowerCase();
        this.modalService.open(content, {
            size: 'sm'
        });
        this.numerettoPiatto = numeretto;
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

    updatePiatto() {
        this.piatto.nome = (document.getElementById('updateNomePiatto') as HTMLInputElement).value;
        this.piatto.prezzo = parseInt((document.getElementById('updatePrezzo') as HTMLInputElement).value);
        this.controlloAllergeniUpdate();
        this.piatto.allergeni = this.allergeni;
        //(document.getElementById('img') as HTMLInputElement).value = this.piatto.img;
        this.piatto.tipologia = (document.getElementById('updateTipologia') as HTMLInputElement).value;
        this.nomeImmagine=(document.getElementById('updateNomeFile') as HTMLInputElement).value;
        if(this.url=='')
        {
            this.piattoService.updatePiatto(this.piatto).subscribe(element => {
                for (var index = 0; index < this.listiPiattini.length; index++) {
                    if (this.listiPiattini[index].numero == element.numero) {
                        this.listiPiattini[index] = element;
                        this.listiPiattini = [...this.listiPiattini];
                    }
                }
            });
        }
        else {
            let input = new FormData();
            input.append('nomePiatto', this.piatto.nome);
            input.append('prezzoPiatto', this.piatto.prezzo + '');
            input.append('numeroPiatto', this.piatto.numero + '');
            input.append('allergeniPiatto', this.piatto.allergeni);
            input.append('imgPiatto', this.immaginePiatto);
            input.append('tipologiaPiatto', this.piatto.tipologia);
            input.append('nomeFile', this.nomeImmagine);
            this.piattoService.updatePiattoConFoto(input).subscribe(element => {
                for (var index = 0; index < this.listiPiattini.length; index++) {
                    if (this.listiPiattini[index].numero == element.numero) {
                        this.listiPiattini[index] = element;
                        this.listiPiattini = [...this.listiPiattini];
                    }
                }
            });
        }
        this.allergeni = '';
        this.url = '';
        this.nomeImmagine="";
        this.modalService.dismissAll();
        //window.location.reload();
    }

    createPiatto() {
        //aggoiungere controllo dei dati
        if (this.controlloInputCreate()) {
            return;
        }
        if (this.piattoService.getEsiste(this.piatto.numero)) {
            (document.getElementById('ControlloNumeretto') as HTMLInputElement).innerHTML = 'Numeretto già esistente';
            document.getElementById('ControlloNumeretto').style.color = '#FF0000';
            document.getElementById('ControlloNumeretto').removeAttribute('hidden');
            return;
        }
        document.getElementById('ControlloNumeretto').setAttribute('hidden', '');
        (document.getElementById('ControlloNumeretto') as HTMLInputElement).value = 'Inserire numeretto';

        this.piatto.nome = (document.getElementById('addPiatto') as HTMLInputElement).value;
        this.piatto.prezzo = parseInt((document.getElementById('addPrezzo') as HTMLInputElement).value);
        this.piatto.numero = parseInt((document.getElementById('addNumerettoPiatto') as HTMLInputElement).value);
        this.controlloAllergeni();
        this.piatto.allergeni = this.allergeni;
        this.nomeImmagine=(document.getElementById('addNomeFile') as HTMLInputElement).value;
        //this.piatto.tipologia=(document.getElementById('addTipologia') as HTMLInputElement).value;
        this.piatto.tipologia = 'Prova';
        let input = new FormData();
        input.append('nomePiatto', this.piatto.nome);
        input.append('prezzoPiatto', this.piatto.prezzo + '');
        input.append('numeroPiatto', this.piatto.numero + '');
        input.append('allergeniPiatto', this.piatto.allergeni);
        input.append('imgPiatto', this.immaginePiatto);
        input.append('tipologiaPiatto', this.piatto.tipologia);
        input.append('nomeFile', this.nomeImmagine);
        this.piattoService.addPiatto(input).subscribe((response: any) => {
            this.listiPiattini.push(response);
            //
            this.ordinaLista();
        });
        this.url = '';
        this.nomeImmagine="";
        this.modalService.dismissAll();
        //window.location.reload();
    }

    deletePiatto(numeretto: number) {
        this.piattoService.deletePiatto(numeretto).subscribe();
        for (var index = 0; index < this.listiPiattini.length; index++) {
            if (this.listiPiattini[index].numero == this.numerettoPiatto) {
                this.listiPiattini.splice(index, 1);
                this.listiPiattini = [...this.listiPiattini];
            }
        }
        this.modalService.dismissAll();
    }

    controlloInputCreate() {
        this.resultControllo = false;
        if ((document.getElementById('addPiatto') as HTMLInputElement).value == '') {
            document.getElementById('ControlloNome').style.color = '#FF0000';
            document.getElementById('ControlloNome').removeAttribute('hidden');
            this.resultControllo = true;
        } else {
            document.getElementById('ControlloNome').setAttribute('hidden', '');
        }
        if ((document.getElementById('addPrezzo') as HTMLInputElement).value == '') {
            document.getElementById('ControlloPrezzo').style.color = '#FF0000';
            document.getElementById('ControlloPrezzo').removeAttribute('hidden');
            this.resultControllo = true;
        } else {
            document.getElementById('ControlloPrezzo').setAttribute('hidden', '');
        }
        if ((document.getElementById('addNumerettoPiatto') as HTMLInputElement).value == '') {
            document.getElementById('ControlloNumeretto').style.color = '#FF0000';
            document.getElementById('ControlloNumeretto').removeAttribute('hidden');
            this.resultControllo = true;
        } else {
            document.getElementById('ControlloNumeretto').setAttribute('hidden', '');
        }
        if ((document.getElementById('addFile') as HTMLInputElement).value == '' ||
            (document.getElementById('addNomeFile') as HTMLInputElement).value == '') {
            document.getElementById('ControlloImmagine').style.color = '#FF0000';
            document.getElementById('ControlloImmagine').removeAttribute('hidden');
            this.resultControllo = true;
        } else {
            document.getElementById('ControlloImmagine').setAttribute('hidden', '');
        }
        return this.resultControllo;
    }


//SIstemare
    controlloAllergeni() {

        if ((document.getElementById('Glutine') as HTMLInputElement).checked == true) {
            this.allergeni += 'Glutine ';
        }
        if ((document.getElementById('Crostacei') as HTMLInputElement).checked == true) {
            this.allergeni += 'Crostacei ';
        }

        if ((document.getElementById('Pesce') as HTMLInputElement).checked == true) {
            this.allergeni += 'Pesce ';
        }
        if ((document.getElementById('Molluschi') as HTMLInputElement).checked == true) {
            this.allergeni += 'Molluschi ';
        }
        if ((document.getElementById('Soia') as HTMLInputElement).checked == true) {
            this.allergeni += 'Soia ';
        }
        if ((document.getElementById('Uova/Derivati') as HTMLInputElement).checked == true) {
            this.allergeni += 'Uova/Derivati ';
        }
        if ((document.getElementById('Verdura') as HTMLInputElement).checked == true) {
            this.allergeni += 'Verdura ';
        }

    }

    riempimentoAllergeniUpdate() {
        if (this.allergeniSingoloPiatto != null || this.allergeniSingoloPiatto != '') {
            if (this.allergeniSingoloPiatto.includes('Glutine')) {
                (document.getElementById('updateGlutine') as HTMLInputElement)
                    .setAttribute('checked', 'true');
            }
            if (this.allergeniSingoloPiatto.includes('Crostacei')) {
                (document.getElementById('updateCrostacei') as HTMLInputElement)
                    .setAttribute('checked', 'true');
            }
            if (this.allergeniSingoloPiatto.includes('Pesce')) {
                (document.getElementById('updatePesce') as HTMLInputElement)
                    .setAttribute('checked', 'true');
            }
            if (this.allergeniSingoloPiatto.includes('Molluschi')) {
                (document.getElementById('updateMolluschi') as HTMLInputElement)
                    .setAttribute('checked', 'true');
            }
            if (this.allergeniSingoloPiatto.includes('Soia')) {
                (document.getElementById('updateSoia') as HTMLInputElement)
                    .setAttribute('checked', 'true');
            }
            if (this.allergeniSingoloPiatto.includes('Uova/Derivati')) {
                (document.getElementById('updateUova/Derivati') as HTMLInputElement)
                    .setAttribute('checked', 'true');
            }
            if (this.allergeniSingoloPiatto.includes('Verdura')) {
                (document.getElementById('updateVerdura') as HTMLInputElement)
                    .setAttribute('checked', 'true');
            }
        }
    }

    controlloAllergeniUpdate() {

        if ((document.getElementById('updateGlutine') as HTMLInputElement).checked == true) {
            this.allergeni += 'Glutine ';
        }
        if ((document.getElementById('updateCrostacei') as HTMLInputElement).checked == true) {
            this.allergeni += 'Crostacei ';
        }
        if ((document.getElementById('updatePesce') as HTMLInputElement).checked == true) {
            this.allergeni += 'Pesce ';
        }
        if ((document.getElementById('updateMolluschi') as HTMLInputElement).checked == true) {
            this.allergeni += 'Molluschi ';
        }
        if ((document.getElementById('updateSoia') as HTMLInputElement).checked == true) {
            this.allergeni += 'Soia ';
        }
        if ((document.getElementById('updateUova/Derivati') as HTMLInputElement).checked == true) {
            this.allergeni += 'Uova/Derivati ';
        }
        if ((document.getElementById('updateVerdura') as HTMLInputElement).checked == true) {
            this.allergeni += 'Verdura ';
        }

    }

    riceviFile(eventTarget: EventTarget) {
        if ((<HTMLInputElement>eventTarget).files && (<HTMLInputElement>eventTarget).files[0]) {
            var reader = new FileReader();

            reader.onload = (event: ProgressEvent) => {
                this.url = (<FileReader> event.target).result;
            };

            reader.readAsDataURL((<HTMLInputElement>eventTarget).files[0]);
        }
        this.immaginePiatto = (<HTMLInputElement>eventTarget).files[0];
    }

    ordinaLista() {
        this.listiPiattini.sort((a: Piatto, b: Piatto) => {
            if (a.numero > b.numero) {
                return 1;
            }
            if (a.numero < b.numero) {
                return -1;
            }
            return 0;
        });
        this.listiPiattini = [...this.listiPiattini];
    }

    /**
     * Metodo per file in cloud
     uploadFileToActivity() {
        this.fileUploadService.postFile(this.fileToUpload).subscribe(data => {
            // do something, if upload success
        }, error => {
            console.log(error);
        });
    }

     postFile(fileToUpload: File): Observable<boolean> {
    const endpoint = 'your-destination-url';
    const formData: FormData = new FormData();
    formData.append('fileKey', fileToUpload, fileToUpload.name);
    return this.httpClient
      .post(endpoint, formData, { headers: yourHeadersConfig })
      .map(() => { return true; })
      .catch((e) => this.handleError(e));
}
     */
}
