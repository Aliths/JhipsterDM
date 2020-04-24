import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICommande, Commande } from 'app/shared/model/commande.model';
import { CommandeService } from './commande.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IRestaurant } from 'app/shared/model/restaurant.model';
import { RestaurantService } from 'app/entities/restaurant/restaurant.service';
import { IPanier } from 'app/shared/model/panier.model';
import { PanierService } from 'app/entities/panier/panier.service';
import { IPaiement } from 'app/shared/model/paiement.model';
import { PaiementService } from 'app/entities/paiement/paiement.service';

type SelectableEntity = IUser | IRestaurant | IPanier | IPaiement;

@Component({
  selector: 'jhi-commande-update',
  templateUrl: './commande-update.component.html'
})
export class CommandeUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  restaurants: IRestaurant[] = [];
  paniers: IPanier[] = [];
  paiements: IPaiement[] = [];

  editForm = this.fb.group({
    id: [],
    echeance: [null, [Validators.required]],
    userId: [],
    restaurantId: [],
    panierId: [],
    paiementId: []
  });

  constructor(
    protected commandeService: CommandeService,
    protected userService: UserService,
    protected restaurantService: RestaurantService,
    protected panierService: PanierService,
    protected paiementService: PaiementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commande }) => {
      if (!commande.id) {
        const today = moment().startOf('day');
        commande.echeance = today;
      }

      this.updateForm(commande);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.restaurantService.query().subscribe((res: HttpResponse<IRestaurant[]>) => (this.restaurants = res.body || []));

      this.panierService.query().subscribe((res: HttpResponse<IPanier[]>) => (this.paniers = res.body || []));

      this.paiementService.query().subscribe((res: HttpResponse<IPaiement[]>) => (this.paiements = res.body || []));
    });
  }

  updateForm(commande: ICommande): void {
    this.editForm.patchValue({
      id: commande.id,
      echeance: commande.echeance ? commande.echeance.format(DATE_TIME_FORMAT) : null,
      userId: commande.userId,
      restaurantId: commande.restaurantId,
      panierId: commande.panierId,
      paiementId: commande.paiementId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commande = this.createFromForm();
    if (commande.id !== undefined) {
      this.subscribeToSaveResponse(this.commandeService.update(commande));
    } else {
      this.subscribeToSaveResponse(this.commandeService.create(commande));
    }
  }

  private createFromForm(): ICommande {
    return {
      ...new Commande(),
      id: this.editForm.get(['id'])!.value,
      echeance: this.editForm.get(['echeance'])!.value ? moment(this.editForm.get(['echeance'])!.value, DATE_TIME_FORMAT) : undefined,
      userId: this.editForm.get(['userId'])!.value,
      restaurantId: this.editForm.get(['restaurantId'])!.value,
      panierId: this.editForm.get(['panierId'])!.value,
      paiementId: this.editForm.get(['paiementId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommande>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
