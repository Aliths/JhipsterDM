import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPaiement, Paiement } from 'app/shared/model/paiement.model';
import { PaiementService } from './paiement.service';

@Component({
  selector: 'jhi-paiement-update',
  templateUrl: './paiement-update.component.html'
})
export class PaiementUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    methode: [null, [Validators.required]]
  });

  constructor(protected paiementService: PaiementService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paiement }) => {
      this.updateForm(paiement);
    });
  }

  updateForm(paiement: IPaiement): void {
    this.editForm.patchValue({
      id: paiement.id,
      methode: paiement.methode
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paiement = this.createFromForm();
    if (paiement.id !== undefined) {
      this.subscribeToSaveResponse(this.paiementService.update(paiement));
    } else {
      this.subscribeToSaveResponse(this.paiementService.create(paiement));
    }
  }

  private createFromForm(): IPaiement {
    return {
      ...new Paiement(),
      id: this.editForm.get(['id'])!.value,
      methode: this.editForm.get(['methode'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaiement>>): void {
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
}
