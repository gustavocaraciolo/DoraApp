import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { DoraAppProdutoModule } from './produto/produto.module';
import { DoraAppCategoriaModule } from './categoria/categoria.module';
import { DoraAppPedidoModule } from './pedido/pedido.module';
import { DoraAppItensPedidoModule } from './itens-pedido/itens-pedido.module';
import { DoraAppUsuarioExtraModule } from './usuario-extra/usuario-extra.module';
import { DoraAppTagModule } from './tag/tag.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        DoraAppProdutoModule,
        DoraAppCategoriaModule,
        DoraAppPedidoModule,
        DoraAppItensPedidoModule,
        DoraAppUsuarioExtraModule,
        DoraAppTagModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraAppEntityModule {}
