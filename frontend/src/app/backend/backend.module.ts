import { NgModule } from '@angular/core';
import {BackendRoutingModule} from "./backend-routing.module";
import {BackendComponent} from "./backend.component";
import {FroalaEditorModule, FroalaViewModule} from "angular-froala-wysiwyg";
import { SettingsComponent } from './settings/settings.component';
import {MatButtonModule, MatToolbarModule} from "@angular/material";

@NgModule({
  imports: [
    BackendRoutingModule,
    FroalaEditorModule.forRoot(),
    FroalaViewModule.forRoot(),
    MatButtonModule,
    MatToolbarModule
  ],
      declarations: [BackendComponent, SettingsComponent]
})
export class  BackendModule {}
