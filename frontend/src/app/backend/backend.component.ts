import {Component, OnInit} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {Token} from "../objects/Token";
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-backend',
  templateUrl: './backend.component.html',
  styleUrls: ['./backend.component.css']
})
export class BackendComponent implements OnInit {

  constructor(private router: Router, private cookieService: CookieService, private userService: UserService) {
  }

  public logout() {
    var user = JSON.parse(this.cookieService.get('user'));
    var token = new Token(user.token);

    this.userService.logout(token)
      .then(result => {
        this.cookieService.delete('user');
        this.router.navigate(['/']);
      }, error => {
        console.error(error);
      });
  }

  ngOnInit() {
  }

}
