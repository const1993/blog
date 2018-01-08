import { NgModule } from '@angular/core';
import {EditorComponent} from "./editor.component";
import {Routes, RouterModule} from "@angular/router";

const routes: Routes = [
  {
    path: '',
    component: EditorComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EditorRoutingModule { }
