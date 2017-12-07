import { NgModule } from '@angular/core';
import {BackendComponent} from "./backend.component";
import {FroalaEditorModule, FroalaViewModule} from "angular-froala-wysiwyg";
import {MatButtonModule, MatToolbarModule} from "@angular/material";
import {BackendRoutingModule} from "./backend-routing.module";

@NgModule({
  imports: [
    BackendRoutingModule,
    FroalaEditorModule.forRoot(),
    FroalaViewModule.forRoot(),
    MatButtonModule,
    MatToolbarModule,
    // BrowserAnimationsModule
  ],
      declarations: [BackendComponent]
})
export class  BackendModule {}
