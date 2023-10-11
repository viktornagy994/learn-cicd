import {Component, OnInit} from '@angular/core';

import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatRadioModule} from "@angular/material/radio";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../services/authentication.service";
import {HttpClient} from "@angular/common/http";
import {validationHandler} from "../../../utils/validationHandler";
import {MatSidenavModule} from "@angular/material/sidenav";
import {isAuthenticatedExp} from "../../../utils/isAuthenticatedExp";
import {GoogleSigninButtonModule} from "@abacritt/angularx-social-login";
import {environment} from "../../../../environments/environment";
import {NgIf} from "@angular/common";
import {RecaptchaComponent, RecaptchaFormsModule, RecaptchaModule} from "ng-recaptcha";
import {MatDialog} from "@angular/material/dialog";
import {RegistrationComponent} from "../registration/registration.component";
import {ForgottenPasswordComponent} from "../forgotten-password/forgotten-password.component";

const BASE_URL = environment.BASE_URL

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule, ReactiveFormsModule, MatIconModule, MatSidenavModule, GoogleSigninButtonModule,
    NgIf, RecaptchaModule,RecaptchaFormsModule]
})
export class LoginComponent implements OnInit {

  hide = true;
  loginForm: FormGroup;
  recaptchaKey = `${environment.recaptchaKey}`;
  recaptchaResolved:boolean = false;
  recaptchaResponse = '';
  recaptchaError = '';


  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private router: Router, private authenticationService: AuthenticationService, private http: HttpClient, private dialogRef: MatDialog
              ) {


    this.loginForm = this.formBuilder.group({
      username: ['',Validators.required],
      password: ['',Validators.required],
      recaptcha: [''],
    })
  }


  ngOnInit() {
    RecaptchaComponent.prototype.ngOnDestroy = function() {};
    if (isAuthenticatedExp()){
      localStorage.getItem("user")
      this.hide = true;
    } else localStorage.removeItem("user")
  }

  onSubmit() {
    const data = {...this.loginForm.value};
    this.authenticationService.authenticate(data,this.recaptchaResponse).subscribe(
      response => {
        localStorage.setItem('user', JSON.stringify(response));
        void this.router.navigate(['/home']);
        this.dialogRef.closeAll();

      },
      error => {
        if (error.error.code === 400){
          this.recaptchaError = "Invalid captcha"}

        error.error = {
          fieldErrors: [
            {
              field: 'username',
              message: 'Invalid username or password',
            },
          ],
        };
        validationHandler(error, this.loginForm);
      });


    return false;
  }


   async loginWithOAuth2(provider: string) {
    window.location.href = BASE_URL + '/api/oauth2/authorization/' + provider;
    this.authenticationService.isUsedOAuth=true


  }

  checkCaptchaIsTrue(captchaResponse : string) {
    this.recaptchaResolved = captchaResponse!=null && captchaResponse.length > 0;
    this.recaptchaResponse = captchaResponse;
  }


  goToRegister() {
    this.dialogRef.closeAll();
    this.dialogRef.open(RegistrationComponent);
  }

  goToForgottenPassword() {
    this.dialogRef.closeAll();
    this.dialogRef.open(ForgottenPasswordComponent);
  }
}
