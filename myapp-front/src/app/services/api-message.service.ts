import {Inject, Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Message} from "../models/message.model";
import {Observable} from "rxjs";
import {Partner} from "../models/partner.model";


@Injectable({providedIn: 'root'})
export class ApiMessageService {
  constructor(private http: HttpClient) { }
  private baseUrl = 'http://localhost:8080/api';
  getMessages(page: number, size: number): Observable<any> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<any>(this.baseUrl + '/messages', { params });
  }
  getPartners() {
    return this.http.get<Partner[]>(this.baseUrl + '/partners');
  }
}
