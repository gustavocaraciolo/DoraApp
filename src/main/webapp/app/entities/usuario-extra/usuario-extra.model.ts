import { BaseEntity } from './../../shared';

export class UsuarioExtra implements BaseEntity {
    constructor(
        public id?: number,
        public telefone?: string,
        public userId?: number,
        public tagId?: number,
        public pedidos?: BaseEntity[],
    ) {
    }
}
