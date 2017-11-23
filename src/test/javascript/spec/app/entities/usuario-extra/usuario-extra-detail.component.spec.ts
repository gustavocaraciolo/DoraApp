/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { DoraAppTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { UsuarioExtraDetailComponent } from '../../../../../../main/webapp/app/entities/usuario-extra/usuario-extra-detail.component';
import { UsuarioExtraService } from '../../../../../../main/webapp/app/entities/usuario-extra/usuario-extra.service';
import { UsuarioExtra } from '../../../../../../main/webapp/app/entities/usuario-extra/usuario-extra.model';

describe('Component Tests', () => {

    describe('UsuarioExtra Management Detail Component', () => {
        let comp: UsuarioExtraDetailComponent;
        let fixture: ComponentFixture<UsuarioExtraDetailComponent>;
        let service: UsuarioExtraService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [DoraAppTestModule],
                declarations: [UsuarioExtraDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    UsuarioExtraService,
                    JhiEventManager
                ]
            }).overrideTemplate(UsuarioExtraDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UsuarioExtraDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UsuarioExtraService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new UsuarioExtra(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.usuarioExtra).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
