import {Component} from '@angular/core';



@Component({
  selector: 'app-news-and-deals',
  templateUrl: './news-and-deals.component.html',
  styleUrls: ['./news-and-deals.component.css'],
})
export class NewsAndDealsComponent {
  slides: any[] = new Array(3).fill({id: -1, src: '', title: '', subtitle: ''});
  constructor() {}

  ngOnInit(): void {
    this.slides[0] = {
      id: 0,
      src: './assets/pines.png',
      title: 'The greenest choice',
      subtitle: 'We are committed to save the environment, that is why we operate the most modern,' +
        ' hydrogen-powered, zero emission aircraft.'
    };
    this.slides[1] = {
      id: 1,
      src: './assets/sledding3.png',
      title: 'Sweden awaits',
      subtitle: 'Dog sledding? It\'s only one adventure among the thousand you can experience' +
        ' by travelling to Sweden!'
    }
    this.slides[2] = {
      id: 2,
      src: './assets/dream-deals.png',
      title: 'October escapes',
      subtitle: 'Discover the world anew! Make new autumn, yet green memories with our special flights!'
    }
  }
}
