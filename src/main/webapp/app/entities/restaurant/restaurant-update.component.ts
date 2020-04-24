import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRestaurant, Restaurant } from 'app/shared/model/restaurant.model';
import { RestaurantService } from './restaurant.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-restaurant-update',
  templateUrl: './restaurant-update.component.html'
})
export class RestaurantUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    type: [null, [Validators.required]],
    userId: []
  });

  constructor(
    protected restaurantService: RestaurantService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ restaurant }) => {
      this.updateForm(restaurant);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(restaurant: IRestaurant): void {
    this.editForm.patchValue({
      id: restaurant.id,
      nom: restaurant.nom,
      type: restaurant.type,
      userId: restaurant.userId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const restaurant = this.createFromForm();
    if (restaurant.id !== undefined) {
      this.subscribeToSaveResponse(this.restaurantService.update(restaurant));
    } else {
      this.subscribeToSaveResponse(this.restaurantService.create(restaurant));
    }
  }

  private createFromForm(): IRestaurant {
    return {
      ...new Restaurant(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      type: this.editForm.get(['type'])!.value,
      userId: this.editForm.get(['userId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRestaurant>>): void {
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

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
