import { BaseEntity } from './../../shared';

export class ItensPedido implements BaseEntity {
    constructor(
        public id?: number,
        public desconto?: number,
        public pedidos?: BaseEntity[],
        public produtoId?: number,
    ) {
    }
}
