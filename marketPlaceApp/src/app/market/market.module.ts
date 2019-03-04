import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MarketRoutingModule } from './market-routing.module';
import { OrderComponent } from './order/order.component';
import { MatTabsModule } from '@angular/material/tabs';
import { BuySellMatchComponent } from './buy-sell-match/buy-sell-match.component';
import { MarketCommonComponent } from './market-common/market-common.component';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';


@NgModule({
  imports: [
    CommonModule,
    MarketRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatTabsModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatRadioModule
  ],
  declarations: [OrderComponent, BuySellMatchComponent, MarketCommonComponent],
  exports: [OrderComponent, BuySellMatchComponent, MarketCommonComponent]
})
export class MarketModule { }
