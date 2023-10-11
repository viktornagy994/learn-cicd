import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";
import {isAuthenticatedExp} from "../../utils/isAuthenticatedExp";
import {LoadingUserModel} from "../../models/loading-user-model";
import {LoginComponent} from "../user.handler.components/login/login.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],

})
export class NavbarComponent implements OnInit {

  userDetails!: LoadingUserModel | undefined;



  constructor(private router: Router, private authenticationService: AuthenticationService, private userService: UserService,private dialogRef: MatDialog) {
  }

  ngOnInit() {


    if (isAuthenticatedExp()) {
      localStorage.getItem("user");
    } else {
      localStorage.removeItem('user');
    }

  }

  getInitials(): string {
    let initialsName: string = '';

    if (this.userDetails != undefined && this.userDetails.name != null) {

      let names: string[] = this.userDetails.name.split(' ');
      let numberOfNames: number = names.length
      if (numberOfNames > 2) {
        numberOfNames = 2;
      }
      for (let i = 0; i < numberOfNames; i++) {
        initialsName = initialsName.concat(names[i].charAt(0))
      }
    }

    return initialsName;
  }

isAdmin():boolean{

    if (this.isAuthenticated()&&this.userDetails?.userRoles.includes("ROLE_ADMIN")){

      return true
    }


    return false;
}
  isAuthenticated(): boolean {

    if (!this.userDetails && isAuthenticatedExp()) {

      this.userDetails = JSON.parse(localStorage.getItem('user') || '{}');


    }

    return isAuthenticatedExp();
  }


  login() {
    this.dialogRef.open(LoginComponent,{
    });
    this.isAdmin();
  }

  logout() {
    this.authenticationService.logout().subscribe(() => {
      localStorage.removeItem('user');
      this.userDetails=undefined;
      this.router.navigate(["/home"]);

    })

  }

  toProfile() {
    this.router.navigate(["/my-profile"])
  }


}
