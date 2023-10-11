import { Component } from '@angular/core';
import {MatButtonModule} from "@angular/material/button";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {MatSidenavModule} from "@angular/material/sidenav";
import {environment} from "../../../../environments/environment";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../services/authentication.service";
import {HttpClient} from "@angular/common/http";
import {MatDialog} from "@angular/material/dialog";
import {NgClass, NgIf} from "@angular/common";
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {UserRegisterModel} from "../../../models/user-register-model";
import {UsernameForPasswordResetModel} from "../../../models/username-for-password-reset-model";
import {CompletedRegisterDialogComponent} from "../completed-register-dialog/completed-register-dialog.component";
import {validationHandler} from "../../../utils/validationHandler";
import {
  ForgottenPasswordSendDialogComponent
} from "../forgotten-password-send-dialog/forgotten-password-send-dialog.component";



const BASE_URL = environment.BASE_URL
@Component({
  selector: 'app-forgotten-password',
  templateUrl: './forgotten-password.component.html',
  styleUrls: ['./forgotten-password.component.css'],
  standalone: true,
  imports: [

    MatButtonModule,
    ReactiveFormsModule, MatIconModule, MatSidenavModule, NgIf, MatCardModule, MatInputModule, MatFormFieldModule, NgClass,
  ]
})
export class ForgottenPasswordComponent {
  passwordForm: FormGroup;


  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private router: Router, private authenticationService: AuthenticationService, private http: HttpClient, private dialogRef: MatDialog
  ) {

    this.passwordForm = this.formBuilder.group({
      username: ['',Validators.required],

    })
  }


  sendResetEmail() {
    const data: UsernameForPasswordResetModel = {...this.passwordForm.value};
  this.userService.getEmailForPasswordReset(data).subscribe({
    next: value => {
      this.passwordForm.reset();
      this.dialogRef.closeAll();
      this.dialogRef.open(ForgottenPasswordSendDialogComponent);
      // this.dialogRef.open(CompletedRegisterDialogComponent)

    },
    error: err => {

        validationHandler(err, this.passwordForm);

    },
    complete: () => {}
  })
  }
}
