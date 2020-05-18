import { Component, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { MenuConfig, List } from '../service/ui-data-config.service';
import { Router } from '@angular/router';
import { Subject, Subscription } from 'rxjs';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnDestroy {

  @Input() activeItem: MenuConfig;
  @Input() type: string;
  @Input() list: any;
  @Input() columns: List[];
  @Output() reloadListDashboard = new EventEmitter<any>();

  rows: number;
  selectedRow: any;
  uuid: string;

  subscriptions = new Subscription();
  onDestroy$ = new Subject();

  constructor(
    private router: Router
  ) {
    this.rows = 10;
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
