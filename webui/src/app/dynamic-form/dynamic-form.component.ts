import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormService } from './form.service';
import { DynamicFormService, DynamicFormModel } from '@ng-dynamic-forms/core';
import { MenuConfig, ButtonConfig } from '../service/ui-data-config.service';
import { ApiService } from '../service/api.service';
import * as moment from 'moment';

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.css']
})
export class DynamicFormComponent implements OnInit, OnChanges {

  @Input() activeItem: MenuConfig;
  @Input() displayForm: boolean;
  @Input() buttonConfig: ButtonConfig;
  @Input() formData: any;
  @Input() uuid: string;

  formModel: DynamicFormModel;
  formGroup: FormGroup;

  constructor(
    private formService: FormService,
    private dynamicFormService: DynamicFormService,
    private apiService: ApiService) { }

  ngOnInit() {
    this.formService.getFormModel(this.activeItem.label)
      .subscribe(formModelJSON => {

        this.formModel = this.dynamicFormService.fromJSON(formModelJSON);
        this.formGroup = this.dynamicFormService.createFormGroup(this.formModel);

      });

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
    this.apiService.requestWithBody(this.activeItem.path, this.buttonConfig, formGroupRawValue, this.uuid).subscribe();
  }

  onHide() {
    this.displayForm = false;
  }

}
