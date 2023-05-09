import { Component, OnInit } from '@angular/core';
import { CardService } from '../card.service';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.scss']
})
export class CardsComponent implements OnInit {
  card: any;

  constructor(private dataService: CardService) { }

  ngOnInit(): void {
    this.getCardByPan(123456781112)
  }
  getCardByPan(id: number) {
    this.dataService.getCardByPan(id)
      .subscribe(
        response => {
          this.card = response;
          console.log(response);
        },
        error => {
          console.log(error);
        }
      );
  }

}
