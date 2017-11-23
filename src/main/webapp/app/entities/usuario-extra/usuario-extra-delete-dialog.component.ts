import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UsuarioExtra } from './usuario-extra.model';
import { UsuarioExtraPopupService } from './usuario-extra-popup.service';
import { UsuarioExtraService } from './usuario-extra.service';

@Component({
    selector: 'jhi-usuario-extra-delete-dialog',
    templateUrl: './usuario-extra-delete-dialog.component.html'
})
export class UsuarioExtraDeleteDialogComponent {

    usuarioExtra: UsuarioExtra;

    constructor(
        private usuarioExtraService: UsuarioExtraService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.usuarioExtraService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'usuarioExtraListModification',
                content: 'Deleted an usuarioExtra'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-usuario-extra-delete-popup',
    template: ''
})
export class UsuarioExtraDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private usuarioExtraPopupService: UsuarioExtraPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.usuarioExtraPopupService
                .open(UsuarioExtraDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
