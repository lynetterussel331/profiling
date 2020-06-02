import { Component, OnInit, OnChanges, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormService } from './form.service';
import { DynamicFormService, DynamicFormModel } from '@ng-dynamic-forms/core';
import { MenuConfig, ButtonConfig } from '../service/ui-data-config.service';
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
  @Input() formData: any;
  @Input() uuid: string;

  @Output() sendMessage = new EventEmitter<any>();

  formModel: DynamicFormModel;
  formGroup: FormGroup;

  private subscriptions = new Subscription();
  private onDestroy$ = new Subject();

  constructor(
    private formService: FormService,
    private dynamicFormService: DynamicFormService,
    private apiService: ApiService) { }

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

  ngOnChanges() {
    if (this.formData) {
      Object.keys(this.formGroup.value).forEach(field => {
        let value = this.formData[field];
        if (typeof value !== 'boolean' && moment(new Date(value),  'YYYY-MM-DD').isValid()) {
          value = new Date(value);
        }
        this.formGroup.controls[field].setValue(value);
      });
    }
  }

  onSubmit() {
    const formGroupRawValue = this.formGroup.getRawValue();
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
