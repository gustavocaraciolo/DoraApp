import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CommonModule} from '@angular/common';
import { DoraAppSharedModule } from '../../../shared';
import {GrowlModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
import {ConfirmDialogModule} from 'primeng/components/confirmdialog/confirmdialog';
import {ConfirmationService} from 'primeng/components/common/api';
import {APP_BASE_HREF} from '@angular/common';
import {WizardModule} from 'primeng-extensions-wizard/components/wizard.module';

import {
    ConfirmDialogDemoComponent,
    confirmDialogDemoRoute
} from './';

const primeng_STATES = [
    confirmDialogDemoRoute
];

@NgModule({
    imports: [
        DoraAppSharedModule,
        CommonModule,
        ConfirmDialogModule,
        BrowserAnimationsModule,
        GrowlModule,
        WizardModule,
        ButtonModule,
        RouterModule.forRoot(primeng_STATES, { useHash: true })
    ],
    declarations: [
        ConfirmDialogDemoComponent
    ],
    providers: [{provide: APP_BASE_HREF, useValue: '/'}, ConfirmationService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraAppConfirmDialogDemoModule {}