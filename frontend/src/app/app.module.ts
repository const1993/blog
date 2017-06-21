import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {RouterModule, Routes} from '@angular/router';
import {DataService} from "./data.service";
import {AppRoutingModule} from "./app-routing.module";
import {CustomersComponent} from "./customers/customers.component";
import {CustomerDetailsComponent} from "./customer-details/customer-details.component";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import { BackendComponent } from './backend/backend.component';
// import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { HomepageComponent } from './homepage/homepage.component';

// const appRoutes: Routes = [
//   { path: 'login', component: LoginComponent },
// ];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CustomerDetailsComponent,
    CustomersComponent,
    BackendComponent,
    // BrowserAnimationsModule,
    HomepageComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }

