import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { ApiService } from '../service/api.service';
import { UiDataConfigService, Menu, List } from '../service/ui-data-config.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit, OnChanges {

  @Input() activeItem: Menu;
  list: any;
  columns: List;
  rows: number;
  selectedRow: any;
  cities: any[];
  root = 'sj';

  constructor(
    private uiConfigService: UiDataConfigService,
    private apiService: ApiService,
    private router: Router
  ) {
    this.rows = 10;
  }

  ngOnInit() { }

  ngOnChanges() {
    if (this.activeItem && this.activeItem.label) {
      this.uiConfigService.getMenuConfigDetails(this.activeItem.label)
        .subscribe(data => {
          if (data.label && data.path) {
            this.uiConfigService.getListConfig(data.label).subscribe(config => {
              this.columns = config;
            }, (err) => {
              console.log(err);
            }, () => {
              this.apiService.getList(data.path).subscribe(list => {
                this.list = list;
                console.log('list', this.list);
              });
            });
          }
      });
    }
  }

  redirectToDetails(selectedUUID) {
    this.router.navigate([this.root, this.activeItem.path, selectedUUID]);
  }

}
