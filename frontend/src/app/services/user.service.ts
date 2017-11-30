import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

import { User } from '../objects/user';
import {Credentials} from "../objects/credentials";
import {Token} from "../objects/Token";

@Injectable()
export class UserService {
  url = "/api";

  constructor(private http:Http) {
  }

  findUserByTokenWithPromise(token:Token): Promise<User> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.url + '/checkToken', token, options).toPromise()
      .then(this.extractData)
      .catch(this.handleErrorPromise);
  }

  findUserWithPromise(credentials:Credentials): Promise<User> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.url + '/login', credentials, options).toPromise()
      .then(this.extractData)
      .catch(this.handleErrorPromise);
  }

  private extractData(res: Response) {
    console.log("Response:", res);
    let responseJson = res.json();

    var user = new User();
    user.name = responseJson.name;
    user.surname = responseJson.surname;
    user.email = responseJson.email;
    user.token = responseJson.token;
    user.roles = responseJson.roles;

    return user;
  }

  private handleErrorObservable (error: Response | any) {
    console.error(error.message || error);
    return Observable.throw(error.message || error);
  }

  private handleErrorPromise (error: Response | any) {
    console.error(error.message || error);
    return Promise.reject(error.message || error);
  }
}
