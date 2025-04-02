import {Inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Message} from "../models/message.model";
import {Observable} from "rxjs";
import {Partner} from "../models/partner.model";


@Injectable({providedIn: 'root'})
export class ApiService {
  constructor(private http: HttpClient) { }
  private baseUrl = 'http://localhost:8080/api';
  getMessages(): Observable<Message[]> {
    return this.http.get<Message[]>(this.baseUrl + '/messages');
  }
  getPartners() {
    return this.http.get<Partner[]>(this.baseUrl + '/partners');
  }
}
