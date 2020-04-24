import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommande } from 'app/shared/model/commande.model';

type EntityResponseType = HttpResponse<ICommande>;
type EntityArrayResponseType = HttpResponse<ICommande[]>;

@Injectable({ providedIn: 'root' })
export class CommandeService {
  public resourceUrl = SERVER_API_URL + 'api/commandes';

  constructor(protected http: HttpClient) {}

  create(commande: ICommande): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commande);
    return this.http
      .post<ICommande>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(commande: ICommande): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commande);
    return this.http
      .put<ICommande>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICommande>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICommande[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(commande: ICommande): ICommande {
    const copy: ICommande = Object.assign({}, commande, {
      echeance: commande.echeance && commande.echeance.isValid() ? commande.echeance.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.echeance = res.body.echeance ? moment(res.body.echeance) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((commande: ICommande) => {
        commande.echeance = commande.echeance ? moment(commande.echeance) : undefined;
      });
    }
    return res;
  }
}
