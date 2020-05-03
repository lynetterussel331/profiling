import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { Menu, UiDataConfigService } from '../service/ui-data-config.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  @ViewChildren('tabContainer') tabContainer: QueryList<any>;

  items: any = [];
  activeItem: Menu;

  constructor(
    private uiDataConfig: UiDataConfigService
  ) { }

  ngOnInit() {
    this.uiDataConfig.getMenuConfig().subscribe((data: Menu[]) => {
      data.forEach(item => {
        this.items.push({
          label: item.label, icon: item.icon, path: item.path
        });
      });
      this.activeItem = this.items[1];
    });
  }

  getActiveItem() {
    this.activeItem = this.tabContainer.first.activeItem;
  }
}
