import { ApiResponse } from 'src/app/shared/domain';
import { Component, OnInit } from '@angular/core';
import { BuysellMatchService } from './buysell-match.service';
import { MarketMatch, Order } from '../domain';

@Component({
  selector: 'app-buy-sell-match',
  templateUrl: './buy-sell-match.component.html',
  styleUrls: ['./buy-sell-match.component.css'],
  providers: [BuysellMatchService]
})
export class BuySellMatchComponent implements OnInit {

  matches: MarketMatch[];
  unMatches: Order[];
  timeStamp: Date;
  message: string;
  isInfo = false;

  constructor(private buySellMatchService: BuysellMatchService) { }

  ngOnInit() {
    this.loadBuySellMatch();
  }

  loadBuySellMatch() {
    this.buySellMatchService.getMatches().subscribe(
      (res: ApiResponse) => {
        if ('success' === res.status && res.data) {
          this.matches = res.data['match'];
          this.unMatches = res.data['unmatch'];
          this.isInfo = false;
        } else {
          this.matches = [];
          this.unMatches = [];
          this.isInfo = true;
        }
        this.message = res.reason;
        this.timeStamp = res.timeStamp;
      },
      err => {
        this.isInfo = true;
        this.message = 'Something went wrong try after sometime';
      }
    );

  }

}
