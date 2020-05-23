import { Component, DoCheck, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { MenuConfig, List } from '../service/ui-data-config.service';
import { Router } from '@angular/router';
import { Subject, Subscription } from 'rxjs';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements DoCheck, OnDestroy {

  @Input() activeItem: MenuConfig;
  @Input() type: string;
  @Input() collectionType: string;
  @Input() columns: List[];
  @Input() list: any;
  @Output() reloadListDashboard = new EventEmitter<any>();

  rows: number;
  selectedRow: any;
  uuid: string;

  subscriptions = new Subscription();
  onDestroy$ = new Subject();

  executed: boolean;
  distinctValuesMap = new Map<string, any[]>();
  globalFilterFields: any[];

  nonFilterFieldTypes: any[];

  constructor(
    private router: Router,
    private apiService: ApiService
  ) {
    this.rows = 10;
    this.nonFilterFieldTypes = ['radiobutton'];
  }

  ngDoCheck() {
    if (!this.executed && this.columns) {
      this.globalFilterFields = this.columns.filter(col => !this.nonFilterFieldTypes.includes(col.type)).map(col => col.name);
      const filteredCols = this.columns.filter(col => col.filter).map(col => col.name);
      this.apiService.getDistinctValuesMap(this.activeItem.path, filteredCols)
        .subscribe(data => this.distinctValuesMap = data);
      this.executed = true;
    }
  }

  redirectToDetails(selectedUUID) {
    let pathToRedirect;
    let isRedirect = false;
    if (this.type === 'list') {
      pathToRedirect = this.activeItem.path;
      isRedirect = true;
    } else {
      const mainCol: any = this.columns.filter(col => col.name === 'uuid')[0];
      if (mainCol.parent) {
        pathToRedirect = mainCol.parent.path;
        selectedUUID = this.list.filter(list => list.uuid === selectedUUID)[0][mainCol.parent.name];
        isRedirect = true;
      }
    }
    if (isRedirect) {
      this.router.navigate([pathToRedirect, selectedUUID]);
    }
  }

  ngOnDestroy() {
    this.onDestroy$.next();
    this.onDestroy$.complete();
    this.subscriptions.unsubscribe();
  }

  reloadList() {
    this.reloadListDashboard.emit();
  }

}
