import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutes } from './app.routes';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { SidebarModule } from 'primeng/sidebar';
import { TableModule } from 'primeng/table';
import { TabMenuModule } from 'primeng/tabmenu';
import { RadioButtonModule } from 'primeng/radiobutton';
import { MultiSelectModule } from 'primeng/multiselect';
import { TabViewModule } from 'primeng/tabview';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ListComponent } from './list/list.component';
import { HttpClientModule } from '@angular/common/http';
import { DetailsComponent } from './details/details.component';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { ButtonsComponent } from './buttons/buttons.component';
import { CollectionsComponent } from './collections/collections.component';
import { EncrDecrService } from './service/encr-decr.service';
import { ConfirmationService } from 'primeng/api';
import { FormComponent } from './form/form.component';

@NgModule({
  declarations: [
    AppComponent,
    ButtonsComponent,
    CollectionsComponent,
    DashboardComponent,
    DetailsComponent,
    LoginComponent,
    HomeComponent,
    ListComponent,
    FormComponent
  ],
  imports: [
    AppRoutes,
    BreadcrumbModule,
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    ConfirmDialogModule,
    FormsModule,
    HttpClientModule,
    MultiSelectModule,
    PanelModule,
    RadioButtonModule,
    ReactiveFormsModule,
    SidebarModule,
    TableModule,
    TabMenuModule,
    TabViewModule
  ],
  providers: [ ConfirmationService, EncrDecrService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
