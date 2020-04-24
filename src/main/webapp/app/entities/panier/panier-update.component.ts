import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPanier, Panier } from 'app/shared/model/panier.model';
import { PanierService } from './panier.service';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit/produit.service';

@Component({
  selector: 'jhi-panier-update',
  templateUrl: './panier-update.component.html'
})
export class PanierUpdateComponent implements OnInit {
  isSaving = false;
  produits: IProduit[] = [];

  editForm = this.fb.group({
    id: [],
    etat: [null, [Validators.required]],
    produits: []
  });

  constructor(
    protected panierService: PanierService,
    protected produitService: ProduitService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ panier }) => {
      this.updateForm(panier);

      this.produitService.query().subscribe((res: HttpResponse<IProduit[]>) => (this.produits = res.body || []));
    });
  }

  updateForm(panier: IPanier): void {
    this.editForm.patchValue({
      id: panier.id,
      etat: panier.etat,
      produits: panier.produits
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const panier = this.createFromForm();
    if (panier.id !== undefined) {
      this.subscribeToSaveResponse(this.panierService.update(panier));
    } else {
      this.subscribeToSaveResponse(this.panierService.create(panier));
    }
  }

  private createFromForm(): IPanier {
    return {
      ...new Panier(),
      id: this.editForm.get(['id'])!.value,
      etat: this.editForm.get(['etat'])!.value,
      produits: this.editForm.get(['produits'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPanier>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IProduit): any {
    return item.id;
  }

  getSelected(selectedVals: IProduit[], option: IProduit): IProduit {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
