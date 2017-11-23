import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UsuarioExtraComponent } from './usuario-extra.component';
import { UsuarioExtraDetailComponent } from './usuario-extra-detail.component';
import { UsuarioExtraPopupComponent } from './usuario-extra-dialog.component';
import { UsuarioExtraDeletePopupComponent } from './usuario-extra-delete-dialog.component';

@Injectable()
export class UsuarioExtraResolvePagingParams implements Resolve<any> {

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

export const usuarioExtraRoute: Routes = [
    {
        path: 'usuario-extra',
        component: UsuarioExtraComponent,
        resolve: {
            'pagingParams': UsuarioExtraResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.usuarioExtra.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'usuario-extra/:id',
        component: UsuarioExtraDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.usuarioExtra.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const usuarioExtraPopupRoute: Routes = [
    {
        path: 'usuario-extra-new',
        component: UsuarioExtraPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.usuarioExtra.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'usuario-extra/:id/edit',
        component: UsuarioExtraPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.usuarioExtra.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'usuario-extra/:id/delete',
        component: UsuarioExtraDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'doraApp.usuarioExtra.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
