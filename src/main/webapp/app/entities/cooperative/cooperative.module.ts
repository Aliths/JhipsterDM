import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyblogSharedModule } from 'app/shared/shared.module';
import { CooperativeComponent } from './cooperative.component';
import { CooperativeDetailComponent } from './cooperative-detail.component';
import { CooperativeUpdateComponent } from './cooperative-update.component';
import { CooperativeDeleteDialogComponent } from './cooperative-delete-dialog.component';
import { cooperativeRoute } from './cooperative.route';

@NgModule({
  imports: [MyblogSharedModule, RouterModule.forChild(cooperativeRoute)],
  declarations: [CooperativeComponent, CooperativeDetailComponent, CooperativeUpdateComponent, CooperativeDeleteDialogComponent],
  entryComponents: [CooperativeDeleteDialogComponent]
})
export class MyblogCooperativeModule {}
