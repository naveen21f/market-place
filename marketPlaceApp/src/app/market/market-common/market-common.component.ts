import { Component, OnInit } from '@angular/core';
import { OrderType } from '../domain';

@Component({
  selector: 'app-market-common',
  templateUrl: './market-common.component.html',
  styleUrls: ['./market-common.component.css']
})
export class MarketCommonComponent implements OnInit {

  type = OrderType;
  constructor() { }

  ngOnInit() {
  }

}
