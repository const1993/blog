import { NgModule } from '@angular/core';
import {HomepageComponent} from "./homepage.component";
import {HomepageRoutingModule} from "./homepage-routing.component";

// containers


@NgModule({
  imports: [
      HomepageRoutingModule

  ],
  declarations: [
    HomepageComponent
  ]
})
export class  HomepageModule {}
