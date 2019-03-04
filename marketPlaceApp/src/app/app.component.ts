import { MessageService } from './shared/services/message.service';
import { Component, AfterViewInit } from '@angular/core';
import { delay } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  title = 'marketPlaceApp';
  showLoader = false;
  constructor(private messageService: MessageService) {
  }

  ngAfterViewInit() {
    this.messageService.loader$.pipe(delay(0)).subscribe(
      value => this.showLoader = value
    );
  }

}
