import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPanier, Panier } from 'app/shared/model/panier.model';
import { PanierService } from './panier.service';
import { PanierComponent } from './panier.component';
import { PanierDetailComponent } from './panier-detail.component';
import { PanierUpdateComponent } from './panier-update.component';

@Injectable({ providedIn: 'root' })
export class PanierResolve implements Resolve<IPanier> {
  constructor(private service: PanierService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPanier> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((panier: HttpResponse<Panier>) => {
          if (panier.body) {
            return of(panier.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Panier());
  }
}

export const panierRoute: Routes = [
  {
    path: '',
    component: PanierComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'myblogApp.panier.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PanierDetailComponent,
    resolve: {
      panier: PanierResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'myblogApp.panier.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PanierUpdateComponent,
    resolve: {
      panier: PanierResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'myblogApp.panier.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PanierUpdateComponent,
    resolve: {
      panier: PanierResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'myblogApp.panier.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
