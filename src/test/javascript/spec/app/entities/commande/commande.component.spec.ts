import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MyblogTestModule } from '../../../test.module';
import { CommandeComponent } from 'app/entities/commande/commande.component';
import { CommandeService } from 'app/entities/commande/commande.service';
import { Commande } from 'app/shared/model/commande.model';

describe('Component Tests', () => {
  describe('Commande Management Component', () => {
    let comp: CommandeComponent;
    let fixture: ComponentFixture<CommandeComponent>;
    let service: CommandeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MyblogTestModule],
        declarations: [CommandeComponent]
      })
        .overrideTemplate(CommandeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommandeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommandeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Commande(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.commandes && comp.commandes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
