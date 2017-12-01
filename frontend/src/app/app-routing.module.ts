import {CustomerDetailsComponent} from './customer-details/customer-details.component';
import {RouterModule, Routes} from '@angular/router';
import {FrontComponent} from "./front/front.component";
import {NgModule} from "@angular/core";
import {AuthGuard} from "./backend/auth-guard/auth-guard.service";
import {LoginComponent} from "./login/login.component";
import {CookieService} from "ngx-cookie-service";
import {UserService} from "./services/user.service";

const routes: Routes = [
  {
    path: "", component: FrontComponent, children: [
    {
      path: "home", loadChildren: './homepage/homepage.module#HomepageModule'
    }]
  },
  {
    path: "login", loadChildren: './login/login.module#LoginModule'
  },
  {
    path: "backend", loadChildren: './backend/backend.module#BackendModule', canActivate: [AuthGuard]
  },
  {path: 'detail/:id', component: CustomerDetailsComponent},
  {
    path: "**",  redirectTo: "/home"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule],
  providers: [AuthGuard, CookieService, UserService]
})
export class AppRoutingModule {
}
