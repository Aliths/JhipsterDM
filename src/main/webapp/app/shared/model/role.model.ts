import { Roles } from 'app/shared/model/enumerations/roles.model';

export interface IRole {
  id?: number;
  type?: Roles;
  userId?: number;
}

export class Role implements IRole {
  constructor(public id?: number, public type?: Roles, public userId?: number) {}
}
