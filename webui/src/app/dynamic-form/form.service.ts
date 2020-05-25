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

  getSampleFormModel(): Observable<any> {
    return this.httpClient.get<any>('data/sample-form-model.json', { responseType: 'json' });
  }

}
