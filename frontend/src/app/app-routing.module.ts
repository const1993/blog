import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { HomepageComponent } from './homepage/homepage.component';
import { CustomersComponent } from './customers/customers.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {FrontComponent} from "./front/front.component";
import {BackComponent} from "./back/back.component";

const routes: Routes = [
  {path: "", component:FrontComponent, children:[{
    path: 'home', component: HomepageComponent
  }]
  },
  {path: "backend", component:BackComponent, children:[{
    path: 'customer',  component: CustomersComponent
  }]
  },
   { path: 'detail/:id', component: CustomerDetailsComponent},
   {path: 'login', component: LoginComponent},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes, {useHash: true}) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
