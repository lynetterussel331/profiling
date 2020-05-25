import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormService } from './form.service';
import { DynamicFormService, DynamicFormModel } from '@ng-dynamic-forms/core';

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.css']
})
export class DynamicFormComponent implements OnInit {

  @Input() displayForm: boolean;

  formModel: DynamicFormModel;
  formGroup: FormGroup;

  constructor(
    private formService: FormService,
    private dynamicFormService: DynamicFormService) { }

  ngOnInit() {
    this.formService.getSampleFormModel()
      .subscribe(formModelJSON => {

        this.formModel = this.dynamicFormService.fromJSON(formModelJSON);
        this.formGroup = this.dynamicFormService.createFormGroup(this.formModel);

      });

  }

  onSubmit() {
    console.log('submitted');
  }

}
