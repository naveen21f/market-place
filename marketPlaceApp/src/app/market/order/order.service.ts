import { ApiResponse } from 'src/app/shared/domain';
import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Order } from '../domain';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable()
export class OrderService {

  readonly baseUrl = '/api/orders';

  constructor(private http: HttpClient) { }

  saveOrder(order: Order) {
    return this.http.post(`${this.baseUrl}`, order);
  }

  getAllOrders() {
    return this.http.get(`${this.baseUrl}`);
  }

  deleteOrder(orderId: string) {
    return this.http.delete(`${this.baseUrl}/${orderId}`, httpOptions);
  }
}
