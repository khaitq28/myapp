import {Inject, Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Partner} from "../models/partner.model";
import {environment} from "../../environments/environment";

@Injectable({providedIn: 'root'})
export class ApiPartnerService {
  constructor(private readonly http: HttpClient) { }
  private readonly baseUrl =  environment.apiUrl;

  getPartners(page: number, size: number): Observable<Partner> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<any>(this.baseUrl + '/partners', { params });
  }

  deletePartner(id: number) {
    return this.http.delete(this.baseUrl + '/partners/' + id);
  }

  savePartner(newPartner: Partner) {
    return this.http.post(this.baseUrl + '/partners', newPartner);
  }
}
