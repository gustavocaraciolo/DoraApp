import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ItensPedidoComponent } from './itens-pedido.component';
import { ItensPedidoDetailComponent } from './itens-pedido-detail.component';
import { ItensPedidoPopupComponent } from './itens-pedido-dialog.component';
import { ItensPedidoDeletePopupComponent } from './itens-pedido-delete-dialog.component';

@Injectable()
export class ItensPedidoResolvePagingParams implements Resolve<any> {

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

export const itensPedidoRoute: Routes = [
    {
        path: 'itens-pedido',
        component: ItensPedidoComponent,
        resolve: {
            'pagingParams': ItensPedidoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.itensPedido.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'itens-pedido/:id',
        component: ItensPedidoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.itensPedido.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itensPedidoPopupRoute: Routes = [
    {
        path: 'itens-pedido-new',
        component: ItensPedidoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.itensPedido.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'itens-pedido/:id/edit',
        component: ItensPedidoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.itensPedido.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'itens-pedido/:id/delete',
        component: ItensPedidoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.itensPedido.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
