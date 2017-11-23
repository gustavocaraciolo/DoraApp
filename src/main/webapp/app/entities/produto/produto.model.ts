import { BaseEntity } from './../../shared';

export class Produto implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public quantidade?: number,
        public precoUnitario?: number,
        public quantidadeEmEstoque?: number,
        public quantidadePedidos?: number,
        public categoriaId?: number,
        public itensPedidos?: BaseEntity[],
    ) {
    }
}
