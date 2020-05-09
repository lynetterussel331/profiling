import { Component, DoCheck, ViewChildren, QueryList } from '@angular/core';
import { Menu, UiDataConfigService, List } from '../service/ui-data-config.service';
import { Subscription, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements DoCheck {

  @ViewChildren('tabContainer') tabContainer: QueryList<any>;

  items: any = [];
  activeItem: Menu;
  type: string;
  subscriptions = new Subscription();
  onDestroy$ = new Subject();

  columns: List[];
  list: any;

  executed: boolean;

  constructor(
    private uiConfigService: UiDataConfigService,
    private apiService: ApiService
  ) {
    this.type = 'list';
    this.uiConfigService.getMenuConfig().subscribe((data: Menu[]) => {
      data.forEach(item => {
        this.items.push({
          label: item.label, icon: item.icon, path: item.path
        });
      });
      this.activeItem = this.items[1];
    });
  }

  ngDoCheck() {
    if (!this.executed && this.activeItem) {
      this.updateListContents();
      this.executed = true;
    }
  }

  updateActiveItem() {
    this.activeItem = this.tabContainer.first.activeItem;
    this.updateListContents();
  }

  updateListContents() {
    if (this.activeItem && this.activeItem.label) {
      this.subscriptions.add(this.uiConfigService.getListConfig(this.activeItem.label)
        .pipe(takeUntil(this.onDestroy$))
          .subscribe(columns => this.columns = columns));
      this.subscriptions.add(this.uiConfigService.getMenuConfigDetailsUsingLabel(this.activeItem.label)
        .pipe(takeUntil(this.onDestroy$))
        .subscribe(menu => {
          if (menu.path) {
            this.subscriptions.add(this.apiService.getList(menu.path)
              .pipe(takeUntil(this.onDestroy$)).subscribe(list => this.list = list));
          }
        })
      );
    }
  }
}
