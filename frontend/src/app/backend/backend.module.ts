import { NgModule } from '@angular/core';
import {BackendComponent} from "./backend.component";
import {FroalaEditorModule, FroalaViewModule} from "angular-froala-wysiwyg";
import {MatButtonModule, MatToolbarModule} from "@angular/material";
import {BackendRoutingModule} from "./backend-routing.module";
import { EditorComponent } from './editor/editor.component';

@NgModule({
  imports: [
    BackendRoutingModule,
    FroalaEditorModule.forRoot(),
    FroalaViewModule.forRoot(),
    MatButtonModule,
    MatToolbarModule
  ],
      declarations: [BackendComponent]
})
export class  BackendModule {}
