import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPanier } from 'app/shared/model/panier.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { PanierService } from './panier.service';
import { PanierDeleteDialogComponent } from './panier-delete-dialog.component';

@Component({
  selector: 'jhi-panier',
  templateUrl: './panier.component.html'
})
export class PanierComponent implements OnInit, OnDestroy {
  paniers: IPanier[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected panierService: PanierService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.paniers = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.panierService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IPanier[]>) => this.paginatePaniers(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.paniers = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPaniers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPanier): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPaniers(): void {
    this.eventSubscriber = this.eventManager.subscribe('panierListModification', () => this.reset());
  }

  delete(panier: IPanier): void {
    const modalRef = this.modalService.open(PanierDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.panier = panier;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginatePaniers(data: IPanier[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.paniers.push(data[i]);
      }
    }
  }
}
