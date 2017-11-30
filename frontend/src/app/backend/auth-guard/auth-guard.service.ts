import { Injectable }     from '@angular/core';
import {CanActivate, Router}    from '@angular/router';
import {CookieService} from "ngx-cookie-service";
import {UserService} from "../../services/user.service";
import {User} from "../../objects/user";
import {Token} from "../../objects/Token";

@Injectable()
export class AuthGuard implements CanActivate {

  user: User;
  constructor(private router: Router, private cookieService: CookieService, private userService: UserService) { }


  canActivate() {

    if(!this.cookieService.check("user")) {
      this.router.navigate(['/login']);
    }

    var user = JSON.parse(this.cookieService.get('user'));
    var token = new Token(user.token);

    console.log('user', user);
    var promice = this.userService.findUserByTokenWithPromise(token)
      .then(result => {
        if (result == null) {
          this.router.navigate(['/login']);
          return false;
        }
        return true;
      }, error => {
        console.error(error);
        this.router.navigate(['/login']);
        return false;
      });

    // return promice.get();
    return promice;
  }
}
