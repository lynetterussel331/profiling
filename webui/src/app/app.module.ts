import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutes } from './app.routes';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { SidebarModule } from 'primeng/sidebar';
import { TableModule } from 'primeng/table';
import { TabMenuModule } from 'primeng/tabmenu';
import { MenuItem } from 'primeng/api';
import { RadioButtonModule } from 'primeng/radiobutton';
import { MultiSelectModule } from 'primeng/multiselect';
import { TabViewModule } from 'primeng/tabview';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ListComponent } from './list/list.component';
import { HttpClientModule } from '@angular/common/http';
import { DetailsComponent } from './details/details.component';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { ButtonsComponent } from './buttons/buttons.component';
import { CollectionsComponent } from './collections/collections.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    DashboardComponent,
    ListComponent,
    DetailsComponent,
    ButtonsComponent,
    CollectionsComponent
  ],
  imports: [
    AppRoutes,
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    FormsModule,
    PanelModule,
    SidebarModule,
    TableModule,
    TabMenuModule,
    HttpClientModule,
    RadioButtonModule,
    MultiSelectModule,
    BreadcrumbModule,
    TabViewModule
  ],
  providers: [ ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
