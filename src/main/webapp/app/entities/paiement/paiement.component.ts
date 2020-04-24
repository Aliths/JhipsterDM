import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPaiement } from 'app/shared/model/paiement.model';
import { PaiementService } from './paiement.service';
import { PaiementDeleteDialogComponent } from './paiement-delete-dialog.component';

@Component({
  selector: 'jhi-paiement',
  templateUrl: './paiement.component.html'
})
export class PaiementComponent implements OnInit, OnDestroy {
  paiements?: IPaiement[];
  eventSubscriber?: Subscription;

  constructor(protected paiementService: PaiementService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.paiementService.query().subscribe((res: HttpResponse<IPaiement[]>) => (this.paiements = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPaiements();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPaiement): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPaiements(): void {
    this.eventSubscriber = this.eventManager.subscribe('paiementListModification', () => this.loadAll());
  }

  delete(paiement: IPaiement): void {
    const modalRef = this.modalService.open(PaiementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paiement = paiement;
  }
}
