import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPanier } from 'app/shared/model/panier.model';

type EntityResponseType = HttpResponse<IPanier>;
type EntityArrayResponseType = HttpResponse<IPanier[]>;

@Injectable({ providedIn: 'root' })
export class PanierService {
  public resourceUrl = SERVER_API_URL + 'api/paniers';

  constructor(protected http: HttpClient) {}

  create(panier: IPanier): Observable<EntityResponseType> {
    return this.http.post<IPanier>(this.resourceUrl, panier, { observe: 'response' });
  }

  update(panier: IPanier): Observable<EntityResponseType> {
    return this.http.put<IPanier>(this.resourceUrl, panier, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPanier>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPanier[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
