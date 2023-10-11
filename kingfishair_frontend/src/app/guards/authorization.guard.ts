import {Injectable} from "@angular/core";
import {CanActivate, Router} from "@angular/router";
import {isAuthenticatedExp} from "../utils/isAuthenticatedExp";


@Injectable({
  providedIn: 'root'
})
export class
AuthorizationGuard implements CanActivate {
  constructor(private router: Router) {
  }

  canActivate(): boolean {
    if (!isAuthenticatedExp()) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
