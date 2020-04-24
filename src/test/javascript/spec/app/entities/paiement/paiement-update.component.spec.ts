import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MyblogTestModule } from '../../../test.module';
import { PaiementUpdateComponent } from 'app/entities/paiement/paiement-update.component';
import { PaiementService } from 'app/entities/paiement/paiement.service';
import { Paiement } from 'app/shared/model/paiement.model';

describe('Component Tests', () => {
  describe('Paiement Management Update Component', () => {
    let comp: PaiementUpdateComponent;
    let fixture: ComponentFixture<PaiementUpdateComponent>;
    let service: PaiementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyblogTestModule],
        declarations: [PaiementUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PaiementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PaiementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PaiementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Paiement(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Paiement();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
