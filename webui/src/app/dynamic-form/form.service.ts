import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getFormModel(item: string): Observable<any> {
    return this.httpClient.get<any>(`data/${item}/config/formModel.json`, { responseType: 'json' });
  }

}
