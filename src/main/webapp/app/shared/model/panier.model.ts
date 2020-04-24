import { IProduit } from 'app/shared/model/produit.model';
import { Etat } from 'app/shared/model/enumerations/etat.model';

export interface IPanier {
  id?: number;
  etat?: Etat;
  produits?: IProduit[];
}

export class Panier implements IPanier {
  constructor(public id?: number, public etat?: Etat, public produits?: IProduit[]) {}
}
