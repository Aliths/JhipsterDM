import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MyblogTestModule } from '../../../test.module';
import { PanierUpdateComponent } from 'app/entities/panier/panier-update.component';
import { PanierService } from 'app/entities/panier/panier.service';
import { Panier } from 'app/shared/model/panier.model';

describe('Component Tests', () => {
  describe('Panier Management Update Component', () => {
    let comp: PanierUpdateComponent;
    let fixture: ComponentFixture<PanierUpdateComponent>;
    let service: PanierService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyblogTestModule],
        declarations: [PanierUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PanierUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PanierUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PanierService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Panier(123);
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
        const entity = new Panier();
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
