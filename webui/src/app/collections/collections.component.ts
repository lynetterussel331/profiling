import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UiDataConfigService } from '../service/ui-data-config.service';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-collections',
  templateUrl: './collections.component.html',
  styleUrls: ['./collections.component.css']
})
export class CollectionsComponent implements OnInit {

  uuid: string;
  path: any;
  collections: any;
  collectionDetails: any;
  list: any[];
  rows: number;
  columns: any;
  selectedRow: any;

  constructor(
    public route: ActivatedRoute,
    private uiConfigService: UiDataConfigService,
    private apiService: ApiService
  ) {
    this.rows = 10;
  }

  ngOnInit() {
    const params = this.uiConfigService.getCurrentPageParams(this.route);
    this.path = params.get('item');
    this.uuid = params.get('uuid');
    this.uiConfigService.getMenuConfigDetailsUsingPath(this.path)
      .subscribe(menu => {
        this.uiConfigService.getCollectionConfig(menu.label)
          .subscribe(coll => {
            this.collections = coll;
            console.log('collections', this.collections);
            this.collections.forEach(record => {
              this.uiConfigService.getCollectionListConfig(menu.label, record.name)
                .subscribe(columns => {
                  console.log('columns', columns);
                  this.columns = columns;
                  this.apiService.getDetails(record.path, this.uuid)
                    .subscribe(data => {
                      console.log(record.path, 'data', data);
                      this.list = data;
                      columns.forEach(field => {
                        console.log('data[field]', data[field.name]);
                      });
                    });
                });
            });
          });
    });
  }

}
