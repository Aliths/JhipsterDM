import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICooperative, Cooperative } from 'app/shared/model/cooperative.model';
import { CooperativeService } from './cooperative.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IRestaurant } from 'app/shared/model/restaurant.model';
import { RestaurantService } from 'app/entities/restaurant/restaurant.service';

type SelectableEntity = IUser | IRestaurant;

@Component({
  selector: 'jhi-cooperative-update',
  templateUrl: './cooperative-update.component.html'
})
export class CooperativeUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  restaurants: IRestaurant[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.minLength(2)]],
    users: [],
    restaurants: []
  });

  constructor(
    protected cooperativeService: CooperativeService,
    protected userService: UserService,
    protected restaurantService: RestaurantService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cooperative }) => {
      this.updateForm(cooperative);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.restaurantService.query().subscribe((res: HttpResponse<IRestaurant[]>) => (this.restaurants = res.body || []));
    });
  }

  updateForm(cooperative: ICooperative): void {
    this.editForm.patchValue({
      id: cooperative.id,
      nom: cooperative.nom,
      users: cooperative.users,
      restaurants: cooperative.restaurants
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cooperative = this.createFromForm();
    if (cooperative.id !== undefined) {
      this.subscribeToSaveResponse(this.cooperativeService.update(cooperative));
    } else {
      this.subscribeToSaveResponse(this.cooperativeService.create(cooperative));
    }
  }

  private createFromForm(): ICooperative {
    return {
      ...new Cooperative(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      users: this.editForm.get(['users'])!.value,
      restaurants: this.editForm.get(['restaurants'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICooperative>>): void {
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

  getSelected(selectedVals: SelectableEntity[], option: SelectableEntity): SelectableEntity {
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
