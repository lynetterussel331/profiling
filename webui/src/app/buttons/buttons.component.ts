import { Component, Input, OnDestroy, DoCheck } from '@angular/core';
import { UiDataConfigService, Button, Menu } from '../service/ui-data-config.service';
import { Subject, Subscription } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.css']
})
export class ButtonsComponent implements DoCheck, OnDestroy {

  @Input() activeItem: Menu;
  @Input() type: string;
  buttons: Button;

  executed: boolean;

  private subscriptions = new Subscription();
  private onDestroy$ = new Subject();

  constructor(
    private uiDataConfigService: UiDataConfigService
  ) { }

  ngDoCheck() {
    if (!this.executed && this.activeItem) {
      this.subscriptions.add(this.uiDataConfigService.getButtonsConfig(this.activeItem.label, this.type)
        .pipe(takeUntil(this.onDestroy$))
        .subscribe(buttons => {
          this.buttons = buttons;
        })
      );
      this.executed = true;
    }
  }

  ngOnDestroy() {
    this.onDestroy$.next();
    this.onDestroy$.complete();
    this.subscriptions.unsubscribe();
  }

}
