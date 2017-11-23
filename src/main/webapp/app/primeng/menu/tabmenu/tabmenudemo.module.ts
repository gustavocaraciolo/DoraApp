import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {CommonModule} from '@angular/common';
import { DoraAppSharedModule } from '../../../shared';
import {GrowlModule} from 'primeng/primeng';
import {TabMenuModule} from 'primeng/components/tabmenu/tabmenu';
import {ButtonModule} from 'primeng/components/button/button';
import {WizardModule} from 'primeng-extensions-wizard/components/wizard.module';

import {
    TabMenuDemoComponent,
    tabmenuDemoRoute
} from './';

const primeng_STATES = [
    tabmenuDemoRoute
];

@NgModule({
    imports: [
        DoraAppSharedModule,
        CommonModule,
        BrowserAnimationsModule,
        TabMenuModule,
        GrowlModule,
        ButtonModule,
        WizardModule,
        RouterModule.forRoot(primeng_STATES, { useHash: true })
    ],
    declarations: [
        TabMenuDemoComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraAppTabMenuDemoModule {}
