import {NgModule} from '@angular/core';
import {LoginRoutingModule} from "./login-routing.module";
import {LoginComponent} from "./login.component";
import {
  MatButtonModule, MatMenuModule, MatCardModule, MatToolbarModule,
  MatIconModule, MatInputModule, MatFormFieldModule, MatCommonModule
} from "@angular/material";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";

@NgModule({
  imports: [
    CommonModule,
    MatCommonModule,
    FormsModule,
    ReactiveFormsModule,
    LoginRoutingModule,
    MatButtonModule,
    MatMenuModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
  ],

  declarations: [LoginComponent]
})
export class LoginModule  {}
