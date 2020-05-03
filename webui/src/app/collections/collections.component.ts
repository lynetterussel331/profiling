import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UiDataConfigService } from '../service/ui-data-config.service';

@Component({
  selector: 'app-collections',
  templateUrl: './collections.component.html',
  styleUrls: ['./collections.component.css']
})
export class CollectionsComponent implements OnInit {

  path: any;
  collections: any;

  constructor(
    private uiConfigService: UiDataConfigService,
    public route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.path = this.uiConfigService.getCurrentPageParams(this.route).get('item');
    this.uiConfigService.getMenuConfigDetailsUsingPath(this.path)
      .subscribe(menu => {
        this.uiConfigService.getCollectionConfig(menu.label)
          .subscribe(coll => {
            this.collections = coll;
            console.log('collections', this.collections);
          });
    });
  }

}
