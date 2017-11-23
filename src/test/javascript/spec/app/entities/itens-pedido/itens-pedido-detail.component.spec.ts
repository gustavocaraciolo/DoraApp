/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { DoraAppTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ItensPedidoDetailComponent } from '../../../../../../main/webapp/app/entities/itens-pedido/itens-pedido-detail.component';
import { ItensPedidoService } from '../../../../../../main/webapp/app/entities/itens-pedido/itens-pedido.service';
import { ItensPedido } from '../../../../../../main/webapp/app/entities/itens-pedido/itens-pedido.model';

describe('Component Tests', () => {

    describe('ItensPedido Management Detail Component', () => {
        let comp: ItensPedidoDetailComponent;
        let fixture: ComponentFixture<ItensPedidoDetailComponent>;
        let service: ItensPedidoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [DoraAppTestModule],
                declarations: [ItensPedidoDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ItensPedidoService,
                    JhiEventManager
                ]
            }).overrideTemplate(ItensPedidoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItensPedidoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItensPedidoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ItensPedido(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.itensPedido).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
