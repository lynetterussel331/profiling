import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UiDataConfigService, Menu } from '../service/ui-data-config.service';
import { ApiService } from '../service/api.service';
import { Subject, Subscription } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-collections',
  templateUrl: './collections.component.html',
  styleUrls: ['./collections.component.css']
})
export class CollectionsComponent implements OnInit, OnDestroy {

  @Input() activeItem: Menu;
  type: string;

  uuid: string;
  path: any;
  collections: any;
  rows: number;
  list: any;
  columns: any;
  selectedRow: any;

  collectionsData = new Map();

  subscriptions = new Subscription();
  onDestroy$ = new Subject();

  executed: boolean;
  index: number;

  constructor(
    public route: ActivatedRoute,
    private uiConfigService: UiDataConfigService,
    private apiService: ApiService
  ) {
    this.rows = 10;
  }

  ngOnInit() {
    const params = this.uiConfigService.getPageParams(this.route);
    this.uuid = params.get('uuid');
    this.path = params.get('item');

    this.uiConfigService.getCollectionConfig(this.activeItem.label)
    .pipe(takeUntil(this.onDestroy$))
    .subscribe((collection: any) => {
      this.collections = collection;
      collection.forEach(record => {
        this.type = record.name;
        this.uiConfigService.getCollectionListConfig(this.activeItem.label, record.name)
          .pipe(takeUntil(this.onDestroy$))
          .subscribe(columns => {
            this.apiService.getDetails(record.path, this.uuid)
              .pipe(takeUntil(this.onDestroy$))
              .subscribe(list => {
                this.collectionsData.set(record.name, { columns, list });
              }, (err) => console.log(err)
              , () => this.setListData(this.collections[0].name));
          });
      });
    });
  }

  handleChange(e) {
    this.index = e.index;
    this.setListData(this.collections[this.index].name);
  }

  setListData(collectionName: string) {
    const data = this.collectionsData.get(collectionName);
    this.list = data.list;
    this.columns = data.columns;
  }

  ngOnDestroy() {
    this.onDestroy$.next();
    this.onDestroy$.complete();
    this.subscriptions.unsubscribe();
  }

}
