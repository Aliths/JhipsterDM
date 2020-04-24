import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MyblogTestModule } from '../../../test.module';
import { PaiementComponent } from 'app/entities/paiement/paiement.component';
import { PaiementService } from 'app/entities/paiement/paiement.service';
import { Paiement } from 'app/shared/model/paiement.model';

describe('Component Tests', () => {
  describe('Paiement Management Component', () => {
    let comp: PaiementComponent;
    let fixture: ComponentFixture<PaiementComponent>;
    let service: PaiementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyblogTestModule],
        declarations: [PaiementComponent]
      })
        .overrideTemplate(PaiementComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PaiementComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PaiementService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Paiement(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paiements && comp.paiements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
