import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// import needed PrimeNG modules here

import { DoraAppSharedModule } from '../../../shared';
import {FormsModule} from '@angular/forms';
import {PanelModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
import {SplitButtonModule} from 'primeng/primeng';
import {GrowlModule} from 'primeng/primeng';
import {WizardModule} from 'primeng-extensions-wizard/components/wizard.module';

import {
    PanelDemoComponent,
    panelDemoRoute
} from './';

const primeng_STATES = [
    panelDemoRoute
];

@NgModule({
    imports: [
        DoraAppSharedModule,
        BrowserAnimationsModule,
        FormsModule,
        PanelModule,
        GrowlModule,
        SplitButtonModule,
        ButtonModule,
        WizardModule,
        RouterModule.forRoot(primeng_STATES, { useHash: true })
    ],
    declarations: [
        PanelDemoComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraAppPanelDemoModule {}
