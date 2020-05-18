import { Component, EventEmitter, Input, OnDestroy, DoCheck, Output } from '@angular/core';
import { UiDataConfigService, Button, MenuConfig, ButtonConfig } from '../service/ui-data-config.service';
import { Subject, Subscription } from 'rxjs';
import { takeUntil, filter } from 'rxjs/operators';
import { ConfirmationService } from 'primeng/api';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.css']
})
export class ButtonsComponent implements DoCheck, OnDestroy {

  @Input() activeItem: MenuConfig;
  @Input() type: string;
  @Input() uuid: string;
  buttons: Button[] = [];

  @Output() reloadList = new EventEmitter<any>();

  executed: boolean;

  private subscriptions = new Subscription();
  private onDestroy$ = new Subject();

  constructor(
    private uiConfigService: UiDataConfigService,
    private confirmationService: ConfirmationService,
    private apiService: ApiService
  ) { }

  ngDoCheck() {
    if (!this.executed && this.activeItem) {
      this.subscriptions.add(
        this.uiConfigService.getButtonsConfigList(this.activeItem.label, this.type)
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
    this.subscriptions.add(
      this.uiConfigService.getButtonsConfigList(this.activeItem.label, this.type)
      .pipe(takeUntil(this.onDestroy$))
      .subscribe(buttons => {
        this.buttons.push(buttons);
      })
    );
  }

  clickButton(button: Button) {
    this.subscriptions.add(
      this.uiConfigService.getGlobalButtonConfig(button.label)
      .subscribe(config => {
        if (config.confirmMessage) {
          this.confirm(config);
        }
      })
    );
  }

  ngOnDestroy() {
    this.onDestroy$.next();
    this.onDestroy$.complete();
    this.subscriptions.unsubscribe();
  }

  confirm(config: ButtonConfig) {
    this.confirmationService.confirm({
      message: config.confirmMessage,
      accept: () => {
        this.subscriptions.add(
          this.apiService.request(this.activeItem.path, config, this.uuid).subscribe()
        );
        this.reloadList.emit();
      }
    });
  }

}
