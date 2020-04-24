import { IUser } from 'app/core/user/user.model';
import { IRestaurant } from 'app/shared/model/restaurant.model';

export interface ICooperative {
  id?: number;
  nom?: string;
  users?: IUser[];
  restaurants?: IRestaurant[];
}

export class Cooperative implements ICooperative {
  constructor(public id?: number, public nom?: string, public users?: IUser[], public restaurants?: IRestaurant[]) {}
}
