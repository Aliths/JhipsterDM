import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPaiement } from 'app/shared/model/paiement.model';
import { PaiementService } from './paiement.service';

@Component({
  templateUrl: './paiement-delete-dialog.component.html'
})
export class PaiementDeleteDialogComponent {
  paiement?: IPaiement;

  constructor(protected paiementService: PaiementService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paiementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paiementListModification');
      this.activeModal.close();
    });
  }
}
