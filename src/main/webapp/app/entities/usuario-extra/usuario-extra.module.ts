import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DoraAppSharedModule } from '../../shared';
import { DoraAppAdminModule } from '../../admin/admin.module';
import {
    UsuarioExtraService,
    UsuarioExtraPopupService,
    UsuarioExtraComponent,
    UsuarioExtraDetailComponent,
    UsuarioExtraDialogComponent,
    UsuarioExtraPopupComponent,
    UsuarioExtraDeletePopupComponent,
    UsuarioExtraDeleteDialogComponent,
    usuarioExtraRoute,
    usuarioExtraPopupRoute,
    UsuarioExtraResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...usuarioExtraRoute,
    ...usuarioExtraPopupRoute,
];

@NgModule({
    imports: [
        DoraAppSharedModule,
        DoraAppAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        UsuarioExtraComponent,
        UsuarioExtraDetailComponent,
        UsuarioExtraDialogComponent,
        UsuarioExtraDeleteDialogComponent,
        UsuarioExtraPopupComponent,
        UsuarioExtraDeletePopupComponent,
    ],
    entryComponents: [
        UsuarioExtraComponent,
        UsuarioExtraDialogComponent,
        UsuarioExtraPopupComponent,
        UsuarioExtraDeleteDialogComponent,
        UsuarioExtraDeletePopupComponent,
    ],
    providers: [
        UsuarioExtraService,
        UsuarioExtraPopupService,
        UsuarioExtraResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraAppUsuarioExtraModule {}
