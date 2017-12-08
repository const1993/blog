import { NgModule } from '@angular/core';
import {EditorComponent} from "./editor.component";
import {FroalaEditorModule, FroalaViewModule} from "angular-froala-wysiwyg";
import {EditorRoutingModule} from "./editor-routing.module";

@NgModule({
  imports: [
    EditorRoutingModule,
    FroalaEditorModule.forRoot(),
    FroalaViewModule.forRoot()
  ],
  declarations: [EditorComponent]
})
export class EditorModule { }
