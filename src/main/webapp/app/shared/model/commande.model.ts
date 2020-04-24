import { Moment } from 'moment';

export interface ICommande {
  id?: number;
  echeance?: Moment;
  userId?: number;
  restaurantId?: number;
  panierId?: number;
  paiementId?: number;
}

export class Commande implements ICommande {
  constructor(
    public id?: number,
    public echeance?: Moment,
    public userId?: number,
    public restaurantId?: number,
    public panierId?: number,
    public paiementId?: number
  ) {}
}
