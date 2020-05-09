import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UiDataConfigService, Menu } from '../service/ui-data-config.service';
import { ApiService } from '../service/api.service';
import { Subscription, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

export interface ItemDetails {
  name: string;
  caption: string;
  value?: string;
  hasBadge?: boolean;
}

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class DetailsComponent implements OnInit, OnDestroy {

  activeItem: Menu;
  label: string;
  type: string;
  uuid: string;
  path: string;
  details: ItemDetails[] = [];

  subscriptions = new Subscription();
  onDestroy$ = new Subject();

  constructor(
    public route: ActivatedRoute,
    private uiDataConfigService: UiDataConfigService,
    private apiService: ApiService
  ) {
    this.type = 'details';
    const params = this.uiDataConfigService.getPageParams(this.route);
    this.uuid = params.get('uuid');
    this.path = params.get('item');
  }

  ngOnInit() {
    this.subscriptions.add(
      this.uiDataConfigService.getMenuConfigDetailsUsingPath(this.path)
        .pipe(takeUntil(this.onDestroy$))
        .subscribe(config => {
          this.activeItem = config;
          this.label = config.label;
          this.uiDataConfigService.getDetailsConfig(config.label)
            .pipe(takeUntil(this.onDestroy$))
            .subscribe(details => {
              this.apiService.getDetails(this.path, this.uuid)
                .pipe(takeUntil(this.onDestroy$))
                .subscribe(data => {
                  details.forEach(field => {
                    if (field.caption) {
                      this.details.push({ name: field.name, caption: field.caption, value: data[field.name], hasBadge: field.hasBadge });
                    }
                  });
              });
            });
        })
    );
  }

  ngOnDestroy() {
    this.onDestroy$.next();
    this.onDestroy$.complete();
    this.subscriptions.unsubscribe();
  }

}
