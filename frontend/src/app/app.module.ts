import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {AppRoutingModule} from "./app-routing.module";
import {CustomersComponent} from "./customers/customers.component";
import {CustomerDetailsComponent} from "./customer-details/customer-details.component";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import { HomepageComponent } from './homepage/homepage.component';
import { MdButtonModule, MdCardModule, MdMenuModule, MdToolbarModule, MdIconModule } from '@angular/material';
import { FrontComponent } from './front/front.component';
import {BackComponent} from "../backend/back.component";
import {APP_BASE_HREF} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CustomerDetailsComponent,
    CustomersComponent,
    HomepageComponent,
    FrontComponent,
    BackComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    MdButtonModule,
    MdToolbarModule,
    HttpModule
  ],
  providers: [{provide: APP_BASE_HREF, useValue: '/'}],
  bootstrap: [AppComponent]
})


export class AppModule { }

