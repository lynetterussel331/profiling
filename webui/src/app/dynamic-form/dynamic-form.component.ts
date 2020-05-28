import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormService } from './form.service';
import { DynamicFormService, DynamicFormModel } from '@ng-dynamic-forms/core';
import { MenuConfig, ButtonConfig } from '../service/ui-data-config.service';
import { ApiService } from '../service/api.service';

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
      console.log('formGroup', this.formGroup);
      console.log('formData', this.formData);
      Object.keys(this.formGroup.value).forEach(field => {
        this.formGroup.controls[field].setValue(this.formData[field]);
      });
      console.log('formGroup', this.formGroup);
    }
  }

  onSubmit() {
    const formGroupRawValue = this.formGroup.getRawValue();
    console.log('submitted', formGroupRawValue);
    this.apiService.requestWithBody(this.activeItem.path, this.buttonConfig, formGroupRawValue).subscribe();
  }

  onHide() {
    this.displayForm = false;
    console.log('displayForm', this.displayForm);
  }

}
