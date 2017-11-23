import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { UsuarioExtra } from './usuario-extra.model';
import { UsuarioExtraService } from './usuario-extra.service';

@Component({
    selector: 'jhi-usuario-extra-detail',
    templateUrl: './usuario-extra-detail.component.html'
})
export class UsuarioExtraDetailComponent implements OnInit, OnDestroy {

    usuarioExtra: UsuarioExtra;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private usuarioExtraService: UsuarioExtraService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUsuarioExtras();
    }

    load(id) {
        this.usuarioExtraService.find(id).subscribe((usuarioExtra) => {
            this.usuarioExtra = usuarioExtra;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUsuarioExtras() {
        this.eventSubscriber = this.eventManager.subscribe(
            'usuarioExtraListModification',
            (response) => this.load(this.usuarioExtra.id)
        );
    }
}
