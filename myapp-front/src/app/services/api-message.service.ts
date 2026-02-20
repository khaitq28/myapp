import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";


@Injectable({providedIn: 'root'})
export class ApiMessageService {
  constructor(private readonly http: HttpClient) { }

  private readonly baseUrl =  environment.apiUrl;

  getMessages(page: number, size: number): Observable<any> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<any>(this.baseUrl + '/messages', { params });
  }
}
