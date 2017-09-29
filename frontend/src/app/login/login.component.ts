import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from "@angular/forms";
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';

const EMAIL_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  loginForm: FormGroup;
  email: FormControl;
  password: FormControl;
  http: Http;

  onSubmit() {
    if (this.loginForm.valid) {
      var url = "/login";
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });
      var response = this.http.post(url, "", options)
        .map(this.extractData)
        .catch(this.handleErrorObservable);
      console.log("Form Submitted! email: " + this.email.value + ", pass: " + this.password.value);
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
