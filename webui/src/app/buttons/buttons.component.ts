import { Component, Input, OnDestroy, DoCheck } from '@angular/core';
import { UiDataConfigService, Button, Menu } from '../service/ui-data-config.service';
import { Subject, Subscription } from 'rxjs';
import { takeUntil, filter } from 'rxjs/operators';

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.css']
})
export class ButtonsComponent implements DoCheck, OnDestroy {

  @Input() activeItem: Menu;
  @Input() type: string;
  buttons: Button[] = [];

  executed: boolean;

  private subscriptions = new Subscription();
  private onDestroy$ = new Subject();

  constructor(
    private uiConfigService: UiDataConfigService
  ) { }

  ngDoCheck() {
    if (!this.executed && this.activeItem) {
      this.subscriptions.add(this.uiConfigService.getButtonsConfigList(this.activeItem.label, this.type)
        .pipe( filter(button => !button.displayOnSelect),
          takeUntil(this.onDestroy$))
        .subscribe(buttons => {
          this.buttons.push(buttons);
        })
      );
      this.executed = true;
    }
  }

  clickRadioButton() {
    this.buttons = [];
    this.subscriptions.add(this.uiConfigService.getButtonsConfigList(this.activeItem.label, this.type)
      .pipe(takeUntil(this.onDestroy$))
      .subscribe(buttons => {
        this.buttons.push(buttons);
      })
    );
  }

  ngOnDestroy() {
    this.onDestroy$.next();
    this.onDestroy$.complete();
    this.subscriptions.unsubscribe();
  }

}
