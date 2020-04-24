import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'restaurant',
        loadChildren: () => import('./restaurant/restaurant.module').then(m => m.MyblogRestaurantModule)
      },
      {
        path: 'cooperative',
        loadChildren: () => import('./cooperative/cooperative.module').then(m => m.MyblogCooperativeModule)
      },
      {
        path: 'basket',
        loadChildren: () => import('./basket/basket.module').then(m => m.MyblogBasketModule)
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.MyblogProductModule)
      },
      {
        path: 'payment',
        loadChildren: () => import('./payment/payment.module').then(m => m.MyblogPaymentModule)
      },
      {
        path: 'course',
        loadChildren: () => import('./course/course.module').then(m => m.MyblogCourseModule)
      },
      {
        path: 'order',
        loadChildren: () => import('./order/order.module').then(m => m.MyblogOrderModule)
      },
      {
        path: 'role',
        loadChildren: () => import('./role/role.module').then(m => m.MyblogRoleModule)
      },
      {
        path: 'produit',
        loadChildren: () => import('./produit/produit.module').then(m => m.MyblogProduitModule)
      },
      {
        path: 'panier',
        loadChildren: () => import('./panier/panier.module').then(m => m.MyblogPanierModule)
      },
      {
        path: 'commande',
        loadChildren: () => import('./commande/commande.module').then(m => m.MyblogCommandeModule)
      },
      {
        path: 'paiement',
        loadChildren: () => import('./paiement/paiement.module').then(m => m.MyblogPaiementModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class MyblogEntityModule {}
