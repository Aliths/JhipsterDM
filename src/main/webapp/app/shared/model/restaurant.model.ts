import { ICooperative } from 'app/shared/model/cooperative.model';

export interface IRestaurant {
  id?: number;
  nom?: string;
  type?: string;
  userId?: number;
  cooperatives?: ICooperative[];
}

export class Restaurant implements IRestaurant {
  constructor(
    public id?: number,
    public nom?: string,
    public type?: string,
    public userId?: number,
    public cooperatives?: ICooperative[]
  ) {}
}
