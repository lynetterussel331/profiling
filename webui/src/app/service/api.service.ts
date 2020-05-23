import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ButtonConfig } from './ui-data-config.service';

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

  getDistinctValuesMap(path: string, column: string[]) {
    const url = `/api/${path}/distincts`;
    let params = new HttpParams();
    params = params.append('column', column.join(', '));
    return this.http.get<any>(url, { params });
  }

  request(item: string, config: ButtonConfig, uuid: string, path?: string): Observable<any> {
    let url = `/api/${item}/${uuid}`;

    if (path) {
      url += `/${path}`;
    }

    if (config.action === 'create') {
      // return this.http.post<any>(url);
    } else if (config.action === 'update') {
      // return this.http.put<any>(url);
    } else if (config.action === 'delete') {
      return this.http.delete<any>(url);
    } else if (config.action === 'list') {
      return this.http.get<any>(url);
    }
  }
}
