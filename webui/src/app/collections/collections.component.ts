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
        .subscribe((collections: any) => {
          this.collections = collections;
          this.collections.forEach((collection: Collection) => {
            this.type = collection.name;
            this.subscriptions.add(
              this.apiService.getDetails(collection.path, url.uuid)
                .pipe(takeUntil(this.onDestroy$))
                .subscribe(list => this.collectionsData.set(collection.name, { columns: collection.fields, list }),
                (err) => console.log(err),
                () => this.setListData(this.collections[0].name))
            );
          });
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
