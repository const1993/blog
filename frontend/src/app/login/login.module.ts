import {NgModule} from '@angular/core';
import {LoginRoutingModule} from "./login-routing.module";
import {LoginComponent} from "./login.component";
import {
  MdButtonModule, MdMenuModule, MdCardModule, MdToolbarModule, MdIconModule,
  MdInputModule, MdFormFieldModule, MdCommonModule
} from "@angular/material";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";

// containers

@NgModule({
  imports: [
    CommonModule,
    MdCommonModule,
    FormsModule,
    ReactiveFormsModule,
    LoginRoutingModule,
    MdButtonModule,
    MdMenuModule,
    MdCardModule,
    MdToolbarModule,
    MdIconModule,
    MdInputModule,
    MdFormFieldModule
  ],
  declarations: [LoginComponent
  ]
})
export class LoginModule  {}
