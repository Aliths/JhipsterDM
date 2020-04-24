import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProduit, Produit } from 'app/shared/model/produit.model';
import { ProduitService } from './produit.service';
import { IRestaurant } from 'app/shared/model/restaurant.model';
import { RestaurantService } from 'app/entities/restaurant/restaurant.service';

@Component({
  selector: 'jhi-produit-update',
  templateUrl: './produit-update.component.html'
})
export class ProduitUpdateComponent implements OnInit {
  isSaving = false;
  restaurants: IRestaurant[] = [];

  editForm = this.fb.group({
    id: [],
    prix: [null, [Validators.required, Validators.min(0)]],
    dispo: [null, [Validators.required, Validators.min(0)]],
    nom: [null, [Validators.required]],
    restaurantId: []
  });

  constructor(
    protected produitService: ProduitService,
    protected restaurantService: RestaurantService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ produit }) => {
      this.updateForm(produit);

      this.restaurantService.query().subscribe((res: HttpResponse<IRestaurant[]>) => (this.restaurants = res.body || []));
    });
  }

  updateForm(produit: IProduit): void {
    this.editForm.patchValue({
      id: produit.id,
      prix: produit.prix,
      dispo: produit.dispo,
      nom: produit.nom,
      restaurantId: produit.restaurantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const produit = this.createFromForm();
    if (produit.id !== undefined) {
      this.subscribeToSaveResponse(this.produitService.update(produit));
    } else {
      this.subscribeToSaveResponse(this.produitService.create(produit));
    }
  }

  private createFromForm(): IProduit {
    return {
      ...new Produit(),
      id: this.editForm.get(['id'])!.value,
      prix: this.editForm.get(['prix'])!.value,
      dispo: this.editForm.get(['dispo'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      restaurantId: this.editForm.get(['restaurantId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduit>>): void {
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

  trackById(index: number, item: IRestaurant): any {
    return item.id;
  }
}
