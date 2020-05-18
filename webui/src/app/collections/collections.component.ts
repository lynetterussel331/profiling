import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UiDataConfigService, MenuConfig, Collection } from '../service/ui-data-config.service';
import { ApiService } from '../service/api.service';
import { Subject, Subscription } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { UtilsService } from '../service/utils.service';

@Component({
  selector: 'app-collections',
  templateUrl: './collections.component.html',
  styleUrls: ['./collections.component.css']
})
export class CollectionsComponent implements OnInit, OnDestroy {

  @Input() activeItem: MenuConfig;

  type: string;
  rows: number;
  list: any;
  columns: any;
  selectedRow: any;
  index: number;

  collections: any;
  collectionsData = new Map();

  subscriptions = new Subscription();
  onDestroy$ = new Subject();

  constructor(
    public route: ActivatedRoute,
    private uiConfigService: UiDataConfigService,
    private apiService: ApiService,
    private utilsService: UtilsService
  ) {
    this.rows = 5;
  }

  ngOnInit() {
    const url = this.utilsService.getUrlDetails(this.route);

    this.subscriptions.add(
      this.uiConfigService.getCollectionConfig(this.activeItem.label)
        .pipe(takeUntil(this.onDestroy$))
        .subscribe((collection: any) => {
          this.collections = collection;
          collection.forEach((record: Collection) => {
            this.type = record.name;
            this.getCollectionDetails(record, url.uuid);
          });
        })
    );
  }

  getCollectionDetails(record: Collection, uuid: string) {
    this.subscriptions.add(
      this.uiConfigService.getCollectionListConfig(this.activeItem.label, record.name)
      .pipe(takeUntil(this.onDestroy$))
      .subscribe(columns => {
        this.subscriptions.add(
          this.apiService.getDetails(record.path, uuid)
            .pipe(takeUntil(this.onDestroy$))
            .subscribe(list => {
              this.collectionsData.set(record.name, { columns, list });
            }, (err) => console.log(err)
            , () => this.setListData(this.collections[0].name))
        );
      })
    );
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
