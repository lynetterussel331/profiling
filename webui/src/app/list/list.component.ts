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

  constructor(
    private router: Router
  ) {
    this.rows = 10;
  }

  redirectToDetails(selectedUUID) {
    this.router.navigate([this.root, this.activeItem.path, selectedUUID]);
  }

  ngOnDestroy() {
    this.onDestroy$.next();
    this.onDestroy$.complete();
    this.subscriptions.unsubscribe();
  }

}
