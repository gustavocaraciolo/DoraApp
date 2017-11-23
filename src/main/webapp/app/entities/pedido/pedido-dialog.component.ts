import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Pedido } from './pedido.model';
import { PedidoPopupService } from './pedido-popup.service';
import { PedidoService } from './pedido.service';
import { UsuarioExtra, UsuarioExtraService } from '../usuario-extra';
import { ItensPedido, ItensPedidoService } from '../itens-pedido';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-pedido-dialog',
    templateUrl: './pedido-dialog.component.html'
})
export class PedidoDialogComponent implements OnInit {

    pedido: Pedido;
    isSaving: boolean;

    usuarioextras: UsuarioExtra[];

    itenspedidos: ItensPedido[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private pedidoService: PedidoService,
        private usuarioExtraService: UsuarioExtraService,
        private itensPedidoService: ItensPedidoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.usuarioExtraService.query()
            .subscribe((res: ResponseWrapper) => { this.usuarioextras = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.itensPedidoService.query()
            .subscribe((res: ResponseWrapper) => { this.itenspedidos = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.pedido.id !== undefined) {
            this.subscribeToSaveResponse(
                this.pedidoService.update(this.pedido));
        } else {
            this.subscribeToSaveResponse(
                this.pedidoService.create(this.pedido));
        }
    }

    private subscribeToSaveResponse(result: Observable<Pedido>) {
        result.subscribe((res: Pedido) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Pedido) {
        this.eventManager.broadcast({ name: 'pedidoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUsuarioExtraById(index: number, item: UsuarioExtra) {
        return item.id;
    }

    trackItensPedidoById(index: number, item: ItensPedido) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-pedido-popup',
    template: ''
})
export class PedidoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private pedidoPopupService: PedidoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.pedidoPopupService
                    .open(PedidoDialogComponent as Component, params['id']);
            } else {
                this.pedidoPopupService
                    .open(PedidoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
