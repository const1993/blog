import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { RouterModule, Routes } from '@angular/router';
import {FrontComponent} from "./front/front.component";
import {NgModule} from "@angular/core";
import {AuthGuard} from "./backend/auth-guard/auth-guard.service";

const routes: Routes = [
  {path: "",  component:FrontComponent, children:[
  {
    path: "home", loadChildren: './homepage/homepage.module#HomepageModule'
  }]},
  {path: "backend", loadChildren: './backend/backend.module#BackendModule', canActivate:[AuthGuard]
  },
   { path: 'detail/:id', component: CustomerDetailsComponent},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes, {useHash: true}) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
