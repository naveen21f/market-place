import { OrderType } from './order-type.enum';
import { BaseDomain } from 'src/app/shared/domain';

export class Order extends BaseDomain {
    id: string;
    name: string;
    type: OrderType;
    price: number;

    constructor(type: OrderType) {
        super();
        this.type = type;
    }
}
