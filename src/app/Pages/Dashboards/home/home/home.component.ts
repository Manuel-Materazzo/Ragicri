import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent
    implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  /**Idea lista di oggetti immagine e titolo immagine(salvato nel database)
   * Riempo la lista ricevuta dal database, lato client ciclo la lista e metto nell'img l'url dell'imm
   * e sotto il nome/titolo dell'immagine*/
   //listaImmaginiRistorante: String[]=["../../../../../assets/images/ristorante/ristoranteAperto.jpg",
   //"../../../../../assets/images/ristorante/ristoranteInterni.jpg","../../../../../assets/images/ristorante/sushiCucina.jpg"];
   listaImmaginiRistorante: String[]=["../../../../../assets/images/ristorante/Ristorante1.jpg",
   "../../../../../assets/images/ristorante/Ristorante2.jpg","../../../../../assets/images/ristorante/Ristorante3.jpg"];
  listaImmaginiPiatti: String[]=[]=["../../../../assets/images/piattihome/piattohome1.jpg",
  "../../../../assets/images/piattihome/piattohome2.jpg","../../../../assets/images/piattihome/piattohome4.jpg"];


  /**
  images = [1, 2, 3].map(() => `https://picsum.photos/900/500?random&t=${Math.random()}`);


  slides = [
    {img: '1'},
    {img: '2'},
    {img: '3'},
    {img: '4'},
    {img: '5'},
    {img: '6'},
    {img: '7'},
    {img: '8'},

  ];
  slideConfig = {
    slidesToShow: 1,
    dots: true,
  };

  slideConfig2 = {
    className: 'center',
    centerMode: true,
    infinite: true,
    centerPadding: '60px',
    slidesToShow: 3,
    speed: 500,
    dots: true,
  };

  slideConfig3 = {
    dots: true,
    infinite: false,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 4,
    initialSlide: 0,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 3,
          infinite: true,
          dots: true
        }
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 2,
          initialSlide: 2
        }
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1
        }
      }
    ]
  };

  slideConfig4 = {
    slidesToShow: 3,
    dots: true,
  };

  slideConfig5 = {
    className: 'slider variable-width',
    dots: true,
    infinite: true,
    centerMode: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    variableWidth: true
  };

  slideConfig6 = {
    className: 'center',
    infinite: true,
    slidesToShow: 1,
    speed: 500,
    adaptiveHeight: true,
    dots: true,
  };
   */
}