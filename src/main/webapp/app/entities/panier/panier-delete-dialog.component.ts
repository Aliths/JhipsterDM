import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPanier } from 'app/shared/model/panier.model';
import { PanierService } from './panier.service';

@Component({
  templateUrl: './panier-delete-dialog.component.html'
})
export class PanierDeleteDialogComponent {
  panier?: IPanier;

  constructor(protected panierService: PanierService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.panierService.delete(id).subscribe(() => {
      this.eventManager.broadcast('panierListModification');
      this.activeModal.close();
    });
  }
}
