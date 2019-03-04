import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MarketModule } from './market';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { LoaderInterceptiorService } from './shared/services/loader-interceptior.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    MarketModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatProgressBarModule
  ],
  providers: [ {
    provide: HTTP_INTERCEPTORS,
    useClass: LoaderInterceptiorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
