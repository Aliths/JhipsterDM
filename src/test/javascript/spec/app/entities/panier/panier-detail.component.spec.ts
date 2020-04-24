import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyblogTestModule } from '../../../test.module';
import { PanierDetailComponent } from 'app/entities/panier/panier-detail.component';
import { Panier } from 'app/shared/model/panier.model';

describe('Component Tests', () => {
  describe('Panier Management Detail Component', () => {
    let comp: PanierDetailComponent;
    let fixture: ComponentFixture<PanierDetailComponent>;
    const route = ({ data: of({ panier: new Panier(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyblogTestModule],
        declarations: [PanierDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PanierDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PanierDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load panier on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.panier).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
