import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPaiement } from 'app/shared/model/paiement.model';

type EntityResponseType = HttpResponse<IPaiement>;
type EntityArrayResponseType = HttpResponse<IPaiement[]>;

@Injectable({ providedIn: 'root' })
export class PaiementService {
  public resourceUrl = SERVER_API_URL + 'api/paiements';

  constructor(protected http: HttpClient) {}

  create(paiement: IPaiement): Observable<EntityResponseType> {
    return this.http.post<IPaiement>(this.resourceUrl, paiement, { observe: 'response' });
  }

  update(paiement: IPaiement): Observable<EntityResponseType> {
    return this.http.put<IPaiement>(this.resourceUrl, paiement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPaiement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPaiement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
