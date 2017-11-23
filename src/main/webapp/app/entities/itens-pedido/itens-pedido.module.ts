import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DoraAppSharedModule } from '../../shared';
import {
    ItensPedidoService,
    ItensPedidoPopupService,
    ItensPedidoComponent,
    ItensPedidoDetailComponent,
    ItensPedidoDialogComponent,
    ItensPedidoPopupComponent,
    ItensPedidoDeletePopupComponent,
    ItensPedidoDeleteDialogComponent,
    itensPedidoRoute,
    itensPedidoPopupRoute,
    ItensPedidoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...itensPedidoRoute,
    ...itensPedidoPopupRoute,
];

@NgModule({
    imports: [
        DoraAppSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ItensPedidoComponent,
        ItensPedidoDetailComponent,
        ItensPedidoDialogComponent,
        ItensPedidoDeleteDialogComponent,
        ItensPedidoPopupComponent,
        ItensPedidoDeletePopupComponent,
    ],
    entryComponents: [
        ItensPedidoComponent,
        ItensPedidoDialogComponent,
        ItensPedidoPopupComponent,
        ItensPedidoDeleteDialogComponent,
        ItensPedidoDeletePopupComponent,
    ],
    providers: [
        ItensPedidoService,
        ItensPedidoPopupService,
        ItensPedidoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraAppItensPedidoModule {}
