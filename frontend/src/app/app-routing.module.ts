import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { RouterModule, Routes } from '@angular/router';
import {FrontComponent} from "./front/front.component";
import {BackendComponent} from "./backend/backend.component";
import {CustomersComponent} from "./customers/customers.component";
import {NgModule} from "@angular/core";

const routes: Routes = [
  // {path: "",  component:FrontComponent, children:[{
  //   path: 'home', component: HomepageComponent
  // }]
  // },
  {path: "",  component:FrontComponent, children:[
  {
    path: "home", loadChildren: './homepage/homepage.module#HomepageComponent'
  }]},

  {path: "backend", component:BackendComponent, children:[{
    path: 'customer',  component: CustomersComponent
  }]
  },
   { path: 'detail/:id', component: CustomerDetailsComponent},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes, {useHash: true}) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
