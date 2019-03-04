import { Injectable } from '@angular/core';
import { Subject, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  loader$: BehaviorSubject<boolean> = new BehaviorSubject(false);

  constructor() { }

  showLoader() {
    // console.log('Show Loader');
    this.loader$.next(true);
  }

  hideLoader() {
    // console.log('Hide Loader');
    this.loader$.next(false);
  }
}
