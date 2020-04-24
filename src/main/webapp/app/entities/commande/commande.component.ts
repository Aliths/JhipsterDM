import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICommande } from 'app/shared/model/commande.model';
import { CommandeService } from './commande.service';
import { CommandeDeleteDialogComponent } from './commande-delete-dialog.component';

@Component({
  selector: 'jhi-commande',
  templateUrl: './commande.component.html'
})
export class CommandeComponent implements OnInit, OnDestroy {
  commandes?: ICommande[];
  eventSubscriber?: Subscription;

  constructor(protected commandeService: CommandeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.commandeService.query().subscribe((res: HttpResponse<ICommande[]>) => (this.commandes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCommandes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICommande): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCommandes(): void {
    this.eventSubscriber = this.eventManager.subscribe('commandeListModification', () => this.loadAll());
  }

  delete(commande: ICommande): void {
    const modalRef = this.modalService.open(CommandeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.commande = commande;
  }
}
