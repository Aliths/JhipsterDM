import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPaiement, Paiement } from 'app/shared/model/paiement.model';
import { PaiementService } from './paiement.service';
import { PaiementComponent } from './paiement.component';
import { PaiementDetailComponent } from './paiement-detail.component';
import { PaiementUpdateComponent } from './paiement-update.component';

@Injectable({ providedIn: 'root' })
export class PaiementResolve implements Resolve<IPaiement> {
  constructor(private service: PaiementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPaiement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((paiement: HttpResponse<Paiement>) => {
          if (paiement.body) {
            return of(paiement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Paiement());
  }
}

export const paiementRoute: Routes = [
  {
    path: '',
    component: PaiementComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'myblogApp.paiement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PaiementDetailComponent,
    resolve: {
      paiement: PaiementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'myblogApp.paiement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PaiementUpdateComponent,
    resolve: {
      paiement: PaiementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'myblogApp.paiement.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PaiementUpdateComponent,
    resolve: {
      paiement: PaiementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'myblogApp.paiement.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
