import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {CustomersComponent} from "./customers/customers.component";
import {CustomerDetailsComponent} from "./customer-details/customer-details.component";
import {HttpModule} from "@angular/http";
import { FrontComponent } from './front/front.component';
import {APP_BASE_HREF} from "@angular/common";
import {MatButtonModule, MatCardModule, MatIconModule, MatToolbarModule} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    AppComponent,
    CustomerDetailsComponent,
    CustomersComponent,
    FrontComponent,
  ],
  imports: [
    AppRoutingModule,
    MatButtonModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    BrowserAnimationsModule,
    HttpModule
  ],
  providers: [{provide: APP_BASE_HREF, useValue: '/'}],
  bootstrap: [AppComponent]
})


export class AppModule { }

