import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, filter, flatMap } from 'rxjs/operators';

export interface MenuConfig {
  label: string;
  icon: string;
  path?: string;
}

export interface ButtonConfig {
  label: string;
  action: string;
  confirmMessage?: string;
}

export interface List {
  name: string;
  caption?: string;
  type?: string;
  parent?: Parent;
  hasBadge?: boolean;
}

export interface Parent {
  name: string;
  path: string;
}

export interface Button {
  label: string;
  displayOnSelect?: boolean;
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
  fields: List[];
}

@Injectable({
  providedIn: 'root'
})
export class UiDataConfigService {

  menuConfig: Observable<MenuConfig[]>;
  public menuItem: MenuConfig;

  constructor(
    private httpClient: HttpClient
  ) { }

  getMenuConfig(): Observable<MenuConfig[]> {
    return this.httpClient.get<any>(`data/menuConfig.json`, { responseType: 'json' })
      .pipe(map(config => config.items));
  }

  getMenuConfigList() {
    return this.getMenuConfig().pipe( flatMap(array => array) );
  }

  getMenuConfigDetails(path: string): Observable<MenuConfig> {
    return this.getMenuConfigList().pipe( filter(rec => rec.path === path) );
  }

  getListConfig(item: string): Observable<any> {
    return this.httpClient.get<any>(`data/${item}/config/list.json`, { responseType: 'json' })
      .pipe(map(config => config.list));
  }

  getButtonsConfig(item: string, type: string): Observable<Button[]> {
    return this.httpClient.get<any>(`data/${item}/config/${type}Buttons.json`, { responseType: 'json' })
      .pipe(map(config => config.buttons));
  }

  getButtonsConfigList(item: string, type: string) {
    return this.getButtonsConfig(item, type).pipe( flatMap(array => array) );
  }

  getDetailsConfig(item: string): Observable<Details> {
    return this.httpClient.get<any>(`data/${item}/config/details.json`, { responseType: 'json' })
      .pipe(map(config => config.details));
  }

  getCollectionConfig(item: string): Observable<Collection> {
    return this.httpClient.get<any>(`data/${item}/config/collections.json`, { responseType: 'json' })
      .pipe(map(config => config.collections));
  }

  getGlobalButtonConfig(buttonLabel: string): Observable<any> {
    return this.httpClient.get<any>(`data/buttonsConfig.json`, { responseType: 'json' })
    .pipe(map(config => config.buttons),
      flatMap(array => array),
      filter((config: ButtonConfig) => config.label === buttonLabel));
  }

}
