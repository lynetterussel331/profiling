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
  @Input() collectionType: string;
  @Input() uuid: string;
  @Input() formData: any;

  @Output() reloadList = new EventEmitter<any>();

  buttonConfig: ButtonConfig;
  buttons: Button[] = [];
  executed: boolean;
  displayForm: boolean;

  private subscriptions = new Subscription();
  private onDestroy$ = new Subject();

  constructor(
    private uiConfigService: UiDataConfigService,
    private confirmationService: ConfirmationService,
    private apiService: ApiService
  ) { }

  ngDoCheck() {
    if (!this.executed && this.activeItem) {
      this.loadButtons(this.type);
      this.executed = true;
    }
  }

  clickRadioButton(type: string) {
    this.buttons = [];
    this.loadButtons(type, true);
  }

  loadButtons(type: string, buttonClicked?: boolean) {
    this.subscriptions.add(
      this.uiConfigService.getButtonsConfig(this.activeItem.label, this.type, this.collectionType)
      .pipe(filter((button: Button) => {
        if (['list', 'collections'].includes(type)) {
          return !buttonClicked && button.displayOnSelect ? false : true;
        } else {
          return true;
        }
      }),
        takeUntil(this.onDestroy$))
        .subscribe(buttons => {
          this.buttons.push(buttons);
        })
    );
  }

  clickButton(button: Button) {
    this.subscriptions.add(
      this.uiConfigService.getGlobalButtonConfig(button.label)
      .subscribe(config => {
        this.buttonConfig = config;
        if (config.confirmMessage) {
          this.confirm(config);
        } else {
          this.displayForm = true;
          if (config.action === 'update') {
            this.loadFormContents();
          }
        }
      })
    );
  }

  loadFormContents() {
    console.log('uuid', this.uuid);
    this.subscriptions.add(
      this.apiService.request(this.activeItem.path, 'list', this.uuid)
        .subscribe(data => this.formData = data)
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
          this.apiService.request(this.activeItem.path, config.action, this.uuid).subscribe()
        );
        this.reloadList.emit();
      }
    });
  }

}
