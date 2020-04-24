import { IPanier } from 'app/shared/model/panier.model';

export interface IProduit {
  id?: number;
  prix?: number;
  dispo?: number;
  nom?: string;
  restaurantId?: number;
  paniers?: IPanier[];
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public prix?: number,
    public dispo?: number,
    public nom?: string,
    public restaurantId?: number,
    public paniers?: IPanier[]
  ) {}
}
