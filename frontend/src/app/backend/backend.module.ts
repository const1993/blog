import { NgModule } from '@angular/core';
import {BackendRoutingModule} from "./backend-routing.module";
import {BackendComponent} from "./backend.component";
import {FroalaEditorModule, FroalaViewModule} from "angular-froala-wysiwyg";
import {MdButtonModule, MdToolbarModule} from "@angular/material";
import { SettingsComponent } from './settings/settings.component';

@NgModule({
  imports: [
    BackendRoutingModule,
    FroalaEditorModule.forRoot(),
    FroalaViewModule.forRoot(),
    MdButtonModule,
    MdToolbarModule
  ],
      declarations: [BackendComponent, SettingsComponent]
})
export class  BackendModule {}
