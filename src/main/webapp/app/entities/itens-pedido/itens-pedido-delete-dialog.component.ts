import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ItensPedido } from './itens-pedido.model';
import { ItensPedidoPopupService } from './itens-pedido-popup.service';
import { ItensPedidoService } from './itens-pedido.service';

@Component({
    selector: 'jhi-itens-pedido-delete-dialog',
    templateUrl: './itens-pedido-delete-dialog.component.html'
})
export class ItensPedidoDeleteDialogComponent {

    itensPedido: ItensPedido;

    constructor(
        private itensPedidoService: ItensPedidoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itensPedidoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'itensPedidoListModification',
                content: 'Deleted an itensPedido'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-itens-pedido-delete-popup',
    template: ''
})
export class ItensPedidoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itensPedidoPopupService: ItensPedidoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.itensPedidoPopupService
                .open(ItensPedidoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
