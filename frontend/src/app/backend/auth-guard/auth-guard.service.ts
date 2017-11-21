import { Injectable }     from '@angular/core';
import {CanActivate, Router}    from '@angular/router';
import {CookieService} from "ngx-cookie-service";
import {UserService} from "../../services/user.service";
import {User} from "../../objects/user";

@Injectable()
export class AuthGuard implements CanActivate {

  user: User;
  constructor(private router: Router, private cookieService: CookieService, private userService: UserService) { }


  canActivate() {

    if(!this.cookieService.check("user")) {
      this.router.navigate(['/login']);
    }

    var user = JSON.parse(this.cookieService.get('user'));
    console.log('user', user);
    this.userService.findUserByTokenWithPromise(user)
      .then(result => {
        if (result == null) {
          this.router.navigate(['/login']);
          return false;
        }
        return ;
      }, error => {
        console.error(error);
        this.router.navigate(['/login']);
        return false;
      });

    return false;
  }
}
