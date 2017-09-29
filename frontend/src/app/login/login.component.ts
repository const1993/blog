import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from "@angular/forms";
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import {UserService} from '../services/user.service';
import {Credentials} from '../objects/credentials';
import {User} from "../objects/user";

const EMAIL_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService) { }
  loginForm: FormGroup;
  email: FormControl;
  password: FormControl;
  http: Http;
  credentials = new Credentials();
  user = new User();
  errorMessage: String;

  onSubmit() {
    if (this.loginForm.valid) {
      this.credentials.login = this.email.value;
      this.credentials.password = this.password.value;
       this.userService.findUserWithPromise(this.credentials)
         .then(users => this.user = users[0],
           error =>  this.errorMessage = <any>error);
      console.log("Form Submitted! email: " + this.user.name + ", pass: " + this.user.email);
    }
  }


  private extractData(res: Response) {
    let body = res.json();
    return body.data || {};
  }

  private handleErrorObservable (error: Response | any) {
    console.error(error.message || error);
    return Observable.throw(error.message || error);
  }
  private handleErrorPromise (error: Response | any) {
    console.error(error.message || error);
    return Promise.reject(error.message || error);
  }

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  createFormControls() {
    this.email = new FormControl('', [
      Validators.required,
      Validators.pattern(EMAIL_REGEX)
    ]);
    this.password = new FormControl('', [
      Validators.required,
      Validators.minLength(8)
    ]);
  }

  createForm() {
    this.loginForm = new FormGroup({
      email: this.email,
      password: this.password
    });
  }

}
