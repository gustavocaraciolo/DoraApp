import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ItensPedido } from './itens-pedido.model';
import { ItensPedidoService } from './itens-pedido.service';

@Component({
    selector: 'jhi-itens-pedido-detail',
    templateUrl: './itens-pedido-detail.component.html'
})
export class ItensPedidoDetailComponent implements OnInit, OnDestroy {

    itensPedido: ItensPedido;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private itensPedidoService: ItensPedidoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInItensPedidos();
    }

    load(id) {
        this.itensPedidoService.find(id).subscribe((itensPedido) => {
            this.itensPedido = itensPedido;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInItensPedidos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'itensPedidoListModification',
            (response) => this.load(this.itensPedido.id)
        );
    }
}
