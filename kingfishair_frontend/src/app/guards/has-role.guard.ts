import {ActivatedRouteSnapshot, CanActivate, Router} from "@angular/router";
import {Injectable} from "@angular/core";
import {isAuthenticatedExp} from "../utils/isAuthenticatedExp";

@Injectable({
  providedIn: 'root'
})
export class HasRoleGuard implements CanActivate {
  constructor(public router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles = route.data["expectedRoles"];
    const localStorageUser = localStorage.getItem('user');
    // @ts-ignore
    const localStorageRoles = JSON.parse(localStorageUser).userRoles;
    for (const expectedRole of expectedRoles) {
      if (isAuthenticatedExp() && localStorageRoles.includes(expectedRole)) {
        return true;
      }
    }
    this.router.navigate(['404']);
    return false;
  }
}
