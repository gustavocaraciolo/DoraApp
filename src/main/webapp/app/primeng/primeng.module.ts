
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { DoraAppButtonDemoModule } from './buttons/button/buttondemo.module';
import { DoraAppSplitbuttonDemoModule } from './buttons/splitbutton/splitbuttondemo.module';

import { DoraAppDialogDemoModule } from './overlay/dialog/dialogdemo.module';
import { DoraAppConfirmDialogDemoModule } from './overlay/confirmdialog/confirmdialogdemo.module';
import { DoraAppLightboxDemoModule } from './overlay/lightbox/lightboxdemo.module';
import { DoraAppTooltipDemoModule } from './overlay/tooltip/tooltipdemo.module';
import { DoraAppOverlayPanelDemoModule } from './overlay/overlaypanel/overlaypaneldemo.module';
import { DoraAppSideBarDemoModule } from './overlay/sidebar/sidebardemo.module';

import { DoraAppInputTextDemoModule } from './inputs/inputtext/inputtextdemo.module';
import { DoraAppInputTextAreaDemoModule } from './inputs/inputtextarea/inputtextareademo.module';
import { DoraAppInputGroupDemoModule } from './inputs/inputgroup/inputgroupdemo.module';
import { DoraAppCalendarDemoModule } from './inputs/calendar/calendardemo.module';
import { DoraAppCheckboxDemoModule } from './inputs/checkbox/checkboxdemo.module';
import { DoraAppChipsDemoModule } from './inputs/chips/chipsdemo.module';
import { DoraAppColorPickerDemoModule } from './inputs/colorpicker/colorpickerdemo.module';
import { DoraAppInputMaskDemoModule } from './inputs/inputmask/inputmaskdemo.module';
import { DoraAppInputSwitchDemoModule } from './inputs/inputswitch/inputswitchdemo.module';
import { DoraAppPasswordIndicatorDemoModule } from './inputs/passwordindicator/passwordindicatordemo.module';
import { DoraAppAutoCompleteDemoModule } from './inputs/autocomplete/autocompletedemo.module';
import { DoraAppSliderDemoModule } from './inputs/slider/sliderdemo.module';
import { DoraAppSpinnerDemoModule } from './inputs/spinner/spinnerdemo.module';
import { DoraAppRatingDemoModule } from './inputs/rating/ratingdemo.module';
import { DoraAppSelectDemoModule } from './inputs/select/selectdemo.module';
import { DoraAppSelectButtonDemoModule } from './inputs/selectbutton/selectbuttondemo.module';
import { DoraAppListboxDemoModule } from './inputs/listbox/listboxdemo.module';
import { DoraAppRadioButtonDemoModule } from './inputs/radiobutton/radiobuttondemo.module';
import { DoraAppToggleButtonDemoModule } from './inputs/togglebutton/togglebuttondemo.module';
import { DoraAppEditorDemoModule } from './inputs/editor/editordemo.module';

import { DoraAppGrowlDemoModule } from './messages/growl/growldemo.module';
import { DoraAppMessagesDemoModule } from './messages/messages/messagesdemo.module';
import { DoraAppGalleriaDemoModule } from './multimedia/galleria/galleriademo.module';

import { DoraAppFileUploadDemoModule } from './fileupload/fileupload/fileuploaddemo.module';

import { DoraAppAccordionDemoModule } from './panel/accordion/accordiondemo.module';
import { DoraAppPanelDemoModule } from './panel/panel/paneldemo.module';
import { DoraAppTabViewDemoModule } from './panel/tabview/tabviewdemo.module';
import { DoraAppFieldsetDemoModule } from './panel/fieldset/fieldsetdemo.module';
import { DoraAppToolbarDemoModule } from './panel/toolbar/toolbardemo.module';
import { DoraAppGridDemoModule } from './panel/grid/griddemo.module';

import { DoraAppDataTableDemoModule } from './data/datatable/datatabledemo.module';
import { DoraAppDataGridDemoModule } from './data/datagrid/datagriddemo.module';
import { DoraAppDataListDemoModule } from './data/datalist/datalistdemo.module';
import { DoraAppDataScrollerDemoModule } from './data/datascroller/datascrollerdemo.module';
import { DoraAppPickListDemoModule } from './data/picklist/picklistdemo.module';
import { DoraAppOrderListDemoModule } from './data/orderlist/orderlistdemo.module';
import { DoraAppScheduleDemoModule } from './data/schedule/scheduledemo.module';
import { DoraAppTreeDemoModule } from './data/tree/treedemo.module';
import { DoraAppTreeTableDemoModule } from './data/treetable/treetabledemo.module';
import { DoraAppPaginatorDemoModule } from './data/paginator/paginatordemo.module';
import { DoraAppGmapDemoModule } from './data/gmap/gmapdemo.module';
import { DoraAppOrgChartDemoModule } from './data/orgchart/orgchartdemo.module';
import { DoraAppCarouselDemoModule } from './data/carousel/carouseldemo.module';

import { DoraAppBarchartDemoModule } from './charts/barchart/barchartdemo.module';
import { DoraAppDoughnutchartDemoModule } from './charts/doughnutchart/doughnutchartdemo.module';
import { DoraAppLinechartDemoModule } from './charts/linechart/linechartdemo.module';
import { DoraAppPiechartDemoModule } from './charts/piechart/piechartdemo.module';
import { DoraAppPolarareachartDemoModule } from './charts/polarareachart/polarareachartdemo.module';
import { DoraAppRadarchartDemoModule } from './charts/radarchart/radarchartdemo.module';

import { DoraAppDragDropDemoModule } from './dragdrop/dragdrop/dragdropdemo.module';


import { DoraAppMenuDemoModule } from './menu/menu/menudemo.module';
import { DoraAppContextMenuDemoModule } from './menu/contextmenu/contextmenudemo.module';
import { DoraAppPanelMenuDemoModule } from './menu/panelmenu/panelmenudemo.module';
import { DoraAppStepsDemoModule } from './menu/steps/stepsdemo.module';
import { DoraAppTieredMenuDemoModule } from './menu/tieredmenu/tieredmenudemo.module';
import { DoraAppBreadcrumbDemoModule } from './menu/breadcrumb/breadcrumbdemo.module';
import { DoraAppMegaMenuDemoModule } from './menu/megamenu/megamenudemo.module';
import { DoraAppMenuBarDemoModule } from './menu/menubar/menubardemo.module';
import { DoraAppSlideMenuDemoModule } from './menu/slidemenu/slidemenudemo.module';
import { DoraAppTabMenuDemoModule } from './menu/tabmenu/tabmenudemo.module';

import { DoraAppBlockUIDemoModule } from './misc/blockui/blockuidemo.module';
import { DoraAppCaptchaDemoModule } from './misc/captcha/captchademo.module';
import { DoraAppDeferDemoModule } from './misc/defer/deferdemo.module';
import { DoraAppInplaceDemoModule } from './misc/inplace/inplacedemo.module';
import { DoraAppProgressBarDemoModule } from './misc/progressbar/progressbardemo.module';
import { DoraAppRTLDemoModule } from './misc/rtl/rtldemo.module';
import { DoraAppTerminalDemoModule } from './misc/terminal/terminaldemo.module';
import { DoraAppValidationDemoModule } from './misc/validation/validationdemo.module';
import { DoraAppProgressSpinnerDemoModule } from './misc/progressspinner/progressspinnerdemo.module';

@NgModule({
    imports: [

        DoraAppMenuDemoModule,
        DoraAppContextMenuDemoModule,
        DoraAppPanelMenuDemoModule,
        DoraAppStepsDemoModule,
        DoraAppTieredMenuDemoModule,
        DoraAppBreadcrumbDemoModule,
        DoraAppMegaMenuDemoModule,
        DoraAppMenuBarDemoModule,
        DoraAppSlideMenuDemoModule,
        DoraAppTabMenuDemoModule,

        DoraAppBlockUIDemoModule,
        DoraAppCaptchaDemoModule,
        DoraAppDeferDemoModule,
        DoraAppInplaceDemoModule,
        DoraAppProgressBarDemoModule,
        DoraAppInputMaskDemoModule,
        DoraAppRTLDemoModule,
        DoraAppTerminalDemoModule,
        DoraAppValidationDemoModule,

        DoraAppButtonDemoModule,
        DoraAppSplitbuttonDemoModule,

        DoraAppInputTextDemoModule,
        DoraAppInputTextAreaDemoModule,
        DoraAppInputGroupDemoModule,
        DoraAppCalendarDemoModule,
        DoraAppChipsDemoModule,
        DoraAppInputMaskDemoModule,
        DoraAppInputSwitchDemoModule,
        DoraAppPasswordIndicatorDemoModule,
        DoraAppAutoCompleteDemoModule,
        DoraAppSliderDemoModule,
        DoraAppSpinnerDemoModule,
        DoraAppRatingDemoModule,
        DoraAppSelectDemoModule,
        DoraAppSelectButtonDemoModule,
        DoraAppListboxDemoModule,
        DoraAppRadioButtonDemoModule,
        DoraAppToggleButtonDemoModule,
        DoraAppEditorDemoModule,
        DoraAppColorPickerDemoModule,
        DoraAppCheckboxDemoModule,

        DoraAppGrowlDemoModule,
        DoraAppMessagesDemoModule,
        DoraAppGalleriaDemoModule,

        DoraAppFileUploadDemoModule,

        DoraAppAccordionDemoModule,
        DoraAppPanelDemoModule,
        DoraAppTabViewDemoModule,
        DoraAppFieldsetDemoModule,
        DoraAppToolbarDemoModule,
        DoraAppGridDemoModule,

        DoraAppBarchartDemoModule,
        DoraAppDoughnutchartDemoModule,
        DoraAppLinechartDemoModule,
        DoraAppPiechartDemoModule,
        DoraAppPolarareachartDemoModule,
        DoraAppRadarchartDemoModule,

        DoraAppDragDropDemoModule,

        DoraAppDialogDemoModule,
        DoraAppConfirmDialogDemoModule,
        DoraAppLightboxDemoModule,
        DoraAppTooltipDemoModule,
        DoraAppOverlayPanelDemoModule,
        DoraAppSideBarDemoModule,

        DoraAppDataTableDemoModule,
        DoraAppDataGridDemoModule,
        DoraAppDataListDemoModule,
        DoraAppDataScrollerDemoModule,
        DoraAppScheduleDemoModule,
        DoraAppOrderListDemoModule,
        DoraAppPickListDemoModule,
        DoraAppTreeDemoModule,
        DoraAppTreeTableDemoModule,
        DoraAppPaginatorDemoModule,
        DoraAppOrgChartDemoModule,
        DoraAppGmapDemoModule,
        DoraAppCarouselDemoModule,
        DoraAppProgressSpinnerDemoModule

    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DoraAppprimengModule {}
