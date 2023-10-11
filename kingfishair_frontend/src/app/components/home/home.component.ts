import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {isAuthenticatedExp} from "../../utils/isAuthenticatedExp";
import {NavigationEnd, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {LoginComponent} from "../user.handler.components/login/login.component";
import {SetNewPasswordComponent} from "../user.handler.components/set-new-password/set-new-password.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
routerEvents:any;
  constructor(private authenticationService: AuthenticationService, private router:Router, private dialog:MatDialog) {
    this.routerEvents = this.router.events.subscribe(
      (event:any)=>{
        if (event.url ==='/login'){
          this.dialog.open(LoginComponent);
        }

      }
    )
  }

  openOurFlights() {
    this.router.navigate(['/visible-flight-list']);
  }

  ngOnInit() {
    if (isAuthenticatedExp()) {
      this.authenticationService.findIfAlreadyAuthorized().subscribe(
        response => {
          localStorage.setItem('user', JSON.stringify(response));

        },
        () => {
          localStorage.removeItem('user');

        }
      )
    }
  }


  scrollToTop() {
    window.scrollTo(0,0,);
  }
}

