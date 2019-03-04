import { Order } from './../domain/order';
import { Component, OnInit, Input } from '@angular/core';
import { OrderService } from './order.service';
import { OrderType } from '../domain';
import { ApiResponse } from 'src/app/shared/domain';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
  providers: [OrderService]
})
export class OrderComponent implements OnInit {

  @Input()
  orderType: OrderType = OrderType.BUY;
  order: Order;
  type = OrderType;
  orders: Order[];
  isInfo = false;
  message: string;

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.initOrder();
    this.fetchAllOrders();
  }

  initOrder() {
    this.order = new Order(this.orderType);
  }

  saveOrder() {
   // console.log('Save Order: ' + JSON.stringify(this.order));
    this.orderService.saveOrder(this.order).subscribe(
      (res: ApiResponse) => {
         // console.log('Order Response:  ' + JSON.stringify(res));
          if ('success' === res.status) {
            this.fetchAllOrders();
          } else {
            this.isInfo = true;
            this.message = res.reason;
          }
      },
      err => {
        this.isInfo = true;
        this.message = 'Something went wrong try after sometime';
      },
      () => {

      }
    );
  }

  fetchAllOrders() {
    this.orderService.getAllOrders().subscribe(
      (res: ApiResponse) => {
        if ('success' === res.status) {
          this.orders = res.data;
          this.orders = this.orders.filter(order => this.orderType === order.type);
        } else {
          this.handleError(res.reason);
        }
      },
      err => {
        this.handleError('Something went wrong try after sometime');
      },
      () => {
      }
    );
  }

  deleteOrder(order: Order) {
   // console.log('DeleteOrder');
      this.orderService.deleteOrder(order.id).subscribe(
        (res: ApiResponse) => {
          if ('success' === res.status) {
            this.fetchAllOrders();
          } else {
            this.handleError(res.reason);
          }
        },
        err => {
          this.handleError('Something went wrong try after sometime');
        },
        () => {

        }
      );
  }

  handleError(msg?) {
    this.isInfo = true;
    this.message = msg;
  }

}
