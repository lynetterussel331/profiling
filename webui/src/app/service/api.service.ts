import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    private http: HttpClient
  ) { }

  getList(path: string): Observable<any> {
    const url = `/api/${path}`;
    return this.http.get<any>(url);
  }

  getDetails(path: string, uuid: string): Observable<any> {
    const url = `/api/${path}/${uuid}`;
    return this.http.get<any>(url);
  }
}
