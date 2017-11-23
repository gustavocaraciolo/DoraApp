import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DoraAppSharedModule } from '../../shared';
import {
    PedidoService,
    PedidoPopupService,
    PedidoComponent,
    PedidoDetailComponent,
    PedidoDialogComponent,
    PedidoPopupComponent,
    PedidoDeletePopupComponent,
    PedidoDeleteDialogComponent,
    pedidoRoute,
    pedidoPopupRoute,
    PedidoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...pedidoRoute,
    ...pedidoPopupRoute,
];

@NgModule({
    imports: [
        DoraAppSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PedidoComponent,
        PedidoDetailComponent,
        PedidoDialogComponent,
        PedidoDeleteDialogComponent,
        PedidoPopupComponent,
        PedidoDeletePopupComponent,
    ],
    entryComponents: [
        PedidoComponent,
        PedidoDialogComponent,
        PedidoPopupComponent,
        PedidoDeleteDialogComponent,
        PedidoDeletePopupComponent,
    ],
    providers: [
        PedidoService,
        PedidoPopupService,
        PedidoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraAppPedidoModule {}
