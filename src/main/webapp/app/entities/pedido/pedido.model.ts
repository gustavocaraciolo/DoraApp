import { BaseEntity } from './../../shared';

export class Pedido implements BaseEntity {
    constructor(
        public id?: number,
        public dataPedido?: any,
        public usuarioExtraId?: number,
        public itensPedidoId?: number,
    ) {
    }
}
