import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PedidoComponent } from './pedido.component';
import { PedidoDetailComponent } from './pedido-detail.component';
import { PedidoPopupComponent } from './pedido-dialog.component';
import { PedidoDeletePopupComponent } from './pedido-delete-dialog.component';

@Injectable()
export class PedidoResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const pedidoRoute: Routes = [
    {
        path: 'pedido',
        component: PedidoComponent,
        resolve: {
            'pagingParams': PedidoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.pedido.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'pedido/:id',
        component: PedidoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.pedido.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pedidoPopupRoute: Routes = [
    {
        path: 'pedido-new',
        component: PedidoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.pedido.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pedido/:id/edit',
        component: PedidoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.pedido.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pedido/:id/delete',
        component: PedidoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.pedido.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
