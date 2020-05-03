import { Component, OnInit, Input, OnChanges, OnDestroy } from '@angular/core';
import { UiDataConfigService, Menu, Button } from '../service/ui-data-config.service';
import { Subject } from 'rxjs';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.css']
})
export class ButtonsComponent implements OnInit, OnChanges, OnDestroy {

  private unsubscribe$ = new Subject();

  @Input() activeItem: Menu;
  buttons: Button[] = [];

  constructor(
    private uiDataConfigService: UiDataConfigService
  ) { }

  ngOnInit() { }

  ngOnChanges() {
    if (this.activeItem) {
      this.uiDataConfigService.getButtonsConfig(this.activeItem.label)
        .subscribe(buttons => {
          Object.assign(this.buttons, buttons);
      });
    }
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

}
