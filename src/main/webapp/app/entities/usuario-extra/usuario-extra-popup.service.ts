import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { UsuarioExtra } from './usuario-extra.model';
import { UsuarioExtraService } from './usuario-extra.service';

@Injectable()
export class UsuarioExtraPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private usuarioExtraService: UsuarioExtraService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.usuarioExtraService.find(id).subscribe((usuarioExtra) => {
                    this.ngbModalRef = this.usuarioExtraModalRef(component, usuarioExtra);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.usuarioExtraModalRef(component, new UsuarioExtra());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    usuarioExtraModalRef(component: Component, usuarioExtra: UsuarioExtra): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.usuarioExtra = usuarioExtra;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
