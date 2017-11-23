import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ItensPedido } from './itens-pedido.model';
import { ItensPedidoPopupService } from './itens-pedido-popup.service';
import { ItensPedidoService } from './itens-pedido.service';
import { Produto, ProdutoService } from '../produto';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-itens-pedido-dialog',
    templateUrl: './itens-pedido-dialog.component.html'
})
export class ItensPedidoDialogComponent implements OnInit {

    itensPedido: ItensPedido;
    isSaving: boolean;

    produtos: Produto[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private itensPedidoService: ItensPedidoService,
        private produtoService: ProdutoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.produtoService.query()
            .subscribe((res: ResponseWrapper) => { this.produtos = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.itensPedido.id !== undefined) {
            this.subscribeToSaveResponse(
                this.itensPedidoService.update(this.itensPedido));
        } else {
            this.subscribeToSaveResponse(
                this.itensPedidoService.create(this.itensPedido));
        }
    }

    private subscribeToSaveResponse(result: Observable<ItensPedido>) {
        result.subscribe((res: ItensPedido) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: ItensPedido) {
        this.eventManager.broadcast({ name: 'itensPedidoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackProdutoById(index: number, item: Produto) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-itens-pedido-popup',
    template: ''
})
export class ItensPedidoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itensPedidoPopupService: ItensPedidoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.itensPedidoPopupService
                    .open(ItensPedidoDialogComponent as Component, params['id']);
            } else {
                this.itensPedidoPopupService
                    .open(ItensPedidoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
