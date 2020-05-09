import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, filter, flatMap } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';

export interface Menu {
  label: string;
  icon: string;
  path?: string;
}

export interface List {
  name: string;
  caption?: string;
  hasBadge?: boolean;
}

export interface Button {
  name: string;
}

export interface Details {
  name: string;
  caption: string;
  hasBadge?: boolean;
  forEach(arg0: (field: any) => void);
}

export interface Collection {
  name: string;
  path: string;
}

export interface CollectionList {
  name: string;
  caption: string;
  hasBadge?: boolean;
  forEach(arg0: (element: any) => void);
}

@Injectable({
  providedIn: 'root'
})
export class UiDataConfigService {

  menuConfig: Observable<Menu[]>;
  public menuItem: Menu;

  constructor(
    private httpClient: HttpClient
  ) { }

  getPageParams(route: ActivatedRoute) {
    let paramMap;
    route.paramMap.subscribe(params => paramMap = params);
    return paramMap;
  }

  getMenuConfig(): Observable<Menu[]> {
    return this.httpClient.get<any>(`data/menuConfig.json`, {
      responseType: 'json'
    }).pipe(map(config => config.items));
  }

  getMenuConfigList() {
    return this.getMenuConfig().pipe( flatMap(array => array) );
  }

  getMenuConfigDetailsUsingLabel(label: string): Observable<Menu> {
    return this.getMenuConfigList().pipe( filter(rec => rec.label === label) );
  }

  getMenuConfigDetailsUsingPath(path: string): Observable<Menu> {
    return this.getMenuConfigList().pipe( filter(rec => rec.path === path) );
  }

  getListConfig(item: string): Observable<any> {
    return this.httpClient.get<any>(`data/${item}/config/list.json`, { responseType: 'json' })
      .pipe(map(config => config.list));
  }

  getButtonsConfig(item: string, type: string): Observable<Button> {
    return this.httpClient.get<any>(`data/${item}/config/${type}Buttons.json`, { responseType: 'json' })
      .pipe(map(config => config.buttons));
  }

  getDetailsConfig(item: string): Observable<Details> {
    return this.httpClient.get<any>(`data/${item}/config/details.json`, { responseType: 'json' })
      .pipe(map(config => config.details));
  }

  getCollectionConfig(item: string): Observable<Collection> {
    return this.httpClient.get<any>(`data/${item}/config/collections.json`, { responseType: 'json' })
      .pipe(map(config => config.collections));
  }

  getCollectionListConfig(item: string, collection: string): Observable<CollectionList> {
    return this.httpClient.get<any>(`data/${item}/config/list${collection}.json`, { responseType: 'json' })
      .pipe(map(config => config.list));
  }

}
