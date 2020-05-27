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

    if (config.action === 'delete') {
      return this.http.delete<any>(url);
    } else if (config.action === 'list') {
      return this.http.get<any>(url);
    }
  }

  requestWithBody(item: string, config: ButtonConfig, body: any, uuid?: string): Observable<any> {
    console.log('item', item, 'config', config, 'body', body);
    let url = `/api/${item}`;

    if (uuid) {
      url += `/${uuid}`;
    }

    if (config.action === 'create') {
      console.log('create', url);
      return this.http.post<any>(url, body);
    } else if (config.action === 'update') {
      console.log('update');
      return this.http.put<any>(url, body);
    }
  }

}
