import { MethodPaiement } from 'app/shared/model/enumerations/method-paiement.model';

export interface IPaiement {
  id?: number;
  methode?: MethodPaiement;
}

export class Paiement implements IPaiement {
  constructor(public id?: number, public methode?: MethodPaiement) {}
}
