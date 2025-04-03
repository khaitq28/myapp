import {Inject, Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Message} from "../models/message.model";
import {Observable} from "rxjs";
import {Partner} from "../models/partner.model";


@Injectable({providedIn: 'root'})
export class ApiPartnerService {
  constructor(private http: HttpClient) { }
  private baseUrl = 'http://localhost:8080/api/partners';
  getPartners(page: number, size: number): Observable<any> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<any>(this.baseUrl + '/', { params });
  }

  deletePartner(id: number) {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  savePartner(newPartner: Partner) {
    return this.http.post(this.baseUrl + '/', newPartner);
  }
}
