import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ModuleWithProviders } from '@angular/core';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DetailsComponent } from './details/details.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'sj/:item/:uuid', component: DetailsComponent }
];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes, { enableTracing: false });