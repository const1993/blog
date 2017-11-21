import { NgModule } from '@angular/core';
import {BackendRoutingModule} from "./backend-routing.component";
import {BackendComponent} from "./backend.component";

@NgModule({
  imports: [
    BackendRoutingModule
  ],
  declarations: [BackendComponent
  ]
})
export class  BackendModule {}
