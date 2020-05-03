import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { UiDataConfigService, Details } from '../service/ui-data-config.service';
import { ApiService } from '../service/api.service';

export interface ItemDetails {
  name: string;
  caption: string;
  value?: string;
  hasBadge?: boolean;
}

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit {

  title: string;
  uuid: string;
  details: ItemDetails[] = [];

  constructor(
    public route: ActivatedRoute,
    private uiDataConfigService: UiDataConfigService,
    private apiService: ApiService
  ) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => this.uuid = params.get('uuid'));
    this.route.url.subscribe(value => {
      this.uiDataConfigService.getMenuConfigDetailsUsingPath(value[1].path).subscribe(config => {
        console.log('config', config);
        this.title = config.label;
        this.uiDataConfigService.getDetailsConfig(config.label)
          .subscribe(details => {
            if (config.path) {
              this.apiService.getDetails(config.path, this.uuid)
                .subscribe(data => {
                  details.forEach(field => {
                    if (field.caption) {
                      this.details.push({ name: field.name, caption: field.caption, value: data[field.name], hasBadge: field.hasBadge });
                    }
                  });
              });
            }
            console.log('details', this.details);
          });
      });
    });
  }

}
