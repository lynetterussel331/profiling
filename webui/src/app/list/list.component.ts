import { Component, OnDestroy, Input } from '@angular/core';
import { Menu, List } from '../service/ui-data-config.service';
import { Router } from '@angular/router';
import { Subject, Subscription } from 'rxjs';

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

  rows: number;
  selectedRow: any;
  cities: any[];
  root = 'sj';

  subscriptions = new Subscription();
  onDestroy$ = new Subject();

  selectedValue: string;

  constructor(
    private router: Router,
  ) {
    this.rows = 10;
  }

  selectRadioButton() { }

  redirectToDetails(selectedUUID) {
    let pathToRedirect;
    let uuid;
    if (this.type === 'list') {
      pathToRedirect = this.activeItem.path;
      uuid = selectedUUID;
    } else {
      const mainCol: any = this.columns.filter(col => col.name === 'uuid');
      pathToRedirect = mainCol[0].parent.path;
      const parentField = mainCol[0].parent.name;
      uuid = this.list.filter(list => list.uuid === selectedUUID)[0][parentField];
    }
    this.router.navigate([this.root, pathToRedirect, uuid]);
  }

  ngOnDestroy() {
    this.onDestroy$.next();
    this.onDestroy$.complete();
    this.subscriptions.unsubscribe();
  }

}
