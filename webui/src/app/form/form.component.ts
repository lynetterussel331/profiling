import { Component, OnInit, OnChanges, OnDestroy, Input, Output, EventEmitter, SimpleChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormService } from './form.service';
import { DynamicFormService, DynamicFormModel } from '@ng-dynamic-forms/core';
import { MenuConfig, ButtonConfig, UiDataConfigService } from '../service/ui-data-config.service';
import { ApiService } from '../service/api.service';
import * as moment from 'moment';
import { Subscription, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit, OnChanges, OnDestroy {

  @Input() activeItem: MenuConfig;
  @Input() buttonConfig: ButtonConfig;
  @Input() uuid: string;

  @Output() sendMessage = new EventEmitter<any>();

  formData: any;
  formModel: DynamicFormModel;
  formGroup: FormGroup;

  formHeader: string;

  private subscriptions = new Subscription();
  private onDestroy$ = new Subject();

  constructor(
    private formService: FormService,
    private dynamicFormService: DynamicFormService,
    private apiService: ApiService,
    private uiConfigService: UiDataConfigService) { }

  ngOnInit() {
    this.subscriptions.add(
      this.formService.getFormModel(this.activeItem.label)
        .pipe(takeUntil(this.onDestroy$))
        .subscribe(formModelJSON => {
          this.formModel = this.dynamicFormService.fromJSON(formModelJSON);
          this.formGroup = this.dynamicFormService.createFormGroup(this.formModel);
      })
    );
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.buttonConfig && changes.buttonConfig.currentValue) {

      this.uiConfigService.getItemLabel(this.activeItem.label).subscribe( data => {
        const itemName = data.single;
        this.formHeader = changes.buttonConfig.currentValue.formSettings.caption + ' ' + itemName;
      });

      if (changes.buttonConfig.currentValue.action === 'update') {

        this.subscriptions.add(
          this.apiService.request(this.activeItem.path, 'list', this.uuid)
            .subscribe(data => {
              this.formData = data;
            }, (err) => console.log(err),
            () => {
              Object.keys(this.formGroup.value).forEach(field => {
                let value = this.formData[field];
                if (typeof value !== 'boolean' && moment(new Date(value), 'YYYY-MM-DD').isValid()) {
                  value = new Date(value);
                }
                this.formGroup.controls[field].setValue(value);
              });
            })
        );
      } else {
        this.formGroup.reset();
      }
    }
  }

  onSubmit() {
    const formGroupRawValue = this.formGroup.getRawValue();
    console.log('formGroupRawValue', formGroupRawValue);
    this.subscriptions.add(
      this.apiService.requestWithBody(this.activeItem.path, this.buttonConfig, formGroupRawValue, this.uuid)
        .pipe(takeUntil(this.onDestroy$)).subscribe());
    let successMessage;
    if (this.buttonConfig.action === 'update') {
      successMessage = 'Item updated successfully!';
    } else if (this.buttonConfig.action === 'create') {
      successMessage = 'Item created successfully!';
    }
    this.sendMessage.emit(successMessage);
  }

  ngOnDestroy() {
    this.onDestroy$.next();
    this.onDestroy$.complete();
    this.subscriptions.unsubscribe();
  }

}
