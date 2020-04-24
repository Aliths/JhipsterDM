import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyblogSharedModule } from 'app/shared/shared.module';
import { ProduitComponent } from './produit.component';
import { ProduitDetailComponent } from './produit-detail.component';
import { ProduitUpdateComponent } from './produit-update.component';
import { ProduitDeleteDialogComponent } from './produit-delete-dialog.component';
import { produitRoute } from './produit.route';

@NgModule({
  imports: [MyblogSharedModule, RouterModule.forChild(produitRoute)],
  declarations: [ProduitComponent, ProduitDetailComponent, ProduitUpdateComponent, ProduitDeleteDialogComponent],
  entryComponents: [ProduitDeleteDialogComponent]
})
export class MyblogProduitModule {}
