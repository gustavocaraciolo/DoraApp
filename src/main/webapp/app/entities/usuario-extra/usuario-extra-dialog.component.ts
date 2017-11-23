import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { UsuarioExtra } from './usuario-extra.model';
import { UsuarioExtraPopupService } from './usuario-extra-popup.service';
import { UsuarioExtraService } from './usuario-extra.service';
import { User, UserService } from '../../shared';
import { Tag, TagService } from '../tag';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-usuario-extra-dialog',
    templateUrl: './usuario-extra-dialog.component.html'
})
export class UsuarioExtraDialogComponent implements OnInit {

    usuarioExtra: UsuarioExtra;
    isSaving: boolean;

    users: User[];

    tags: Tag[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private usuarioExtraService: UsuarioExtraService,
        private userService: UserService,
        private tagService: TagService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.tagService.query()
            .subscribe((res: ResponseWrapper) => { this.tags = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.usuarioExtra.id !== undefined) {
            this.subscribeToSaveResponse(
                this.usuarioExtraService.update(this.usuarioExtra));
        } else {
            this.subscribeToSaveResponse(
                this.usuarioExtraService.create(this.usuarioExtra));
        }
    }

    private subscribeToSaveResponse(result: Observable<UsuarioExtra>) {
        result.subscribe((res: UsuarioExtra) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: UsuarioExtra) {
        this.eventManager.broadcast({ name: 'usuarioExtraListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }

    trackTagById(index: number, item: Tag) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-usuario-extra-popup',
    template: ''
})
export class UsuarioExtraPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private usuarioExtraPopupService: UsuarioExtraPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.usuarioExtraPopupService
                    .open(UsuarioExtraDialogComponent as Component, params['id']);
            } else {
                this.usuarioExtraPopupService
                    .open(UsuarioExtraDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
