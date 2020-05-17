import { Component, OnDestroy, Input } from '@angular/core';
import { Menu, List } from '../service/ui-data-config.service';
import { Router } from '@angular/router';
import { Subject, Subscription } from 'rxjs';
import { ButtonsComponent } from '../buttons/buttons.component';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnDestroy {

  @Input() activeItem: Menu;
  @Input() type: string;
  @Input() list: any;
  @Input() columns: List[];
  @Input() buttonComponent: ButtonsComponent;

  rows: number;
  selectedRow: any;
  cities: any[];

  subscriptions = new Subscription();
  onDestroy$ = new Subject();

  selectedValue: string;

  constructor(
    private router: Router
  ) {
    this.rows = 10;
  }

  redirectToDetails(selectedUUID) {
    let isRedirect = false;
    let pathToRedirect;
    let uuid;
    if (this.type === 'list') {
      pathToRedirect = this.activeItem.path;
      uuid = selectedUUID;
      isRedirect = true;
    } else {
      const mainCol: any = this.columns.filter(col => col.name === 'uuid')[0];
      if (mainCol.parent) {
        pathToRedirect = mainCol.parent.path;
        const parentField = mainCol.parent.name;
        uuid = this.list.filter(list => list.uuid === selectedUUID)[0][parentField];
        isRedirect = true;
      }
    }
    if (isRedirect) {
      this.router.navigate([pathToRedirect, uuid]);
    }
  }

  ngOnDestroy() {
    this.onDestroy$.next();
    this.onDestroy$.complete();
    this.subscriptions.unsubscribe();
  }

}
