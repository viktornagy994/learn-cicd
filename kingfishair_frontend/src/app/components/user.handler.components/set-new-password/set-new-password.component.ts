import {Component, OnInit} from '@angular/core';
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgClass, NgIf} from "@angular/common";
import {UserService} from "../../../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../../services/authentication.service";
import {HttpClient} from "@angular/common/http";
import {MatDialog} from "@angular/material/dialog";
import {UserRegisterModel} from "../../../models/user-register-model";
import {NewPasswordModel} from "../../../models/new-password-model";
import {CompletedRegisterDialogComponent} from "../completed-register-dialog/completed-register-dialog.component";
import {LoginComponent} from "../login/login.component";
import {MatIconModule} from "@angular/material/icon";
import {ConfirmedValidator} from "../../../utils/confirmed-validator";
import {UserListItemModel} from "../../../models/user-list-item-model";
import {PasswordResetTokenCheckModel} from "../../../models/password-reset-token-check-model";
import {PasswordValidatorForSpecial} from "../../../utils/password-validator-for-special";
import {PasswordValidatorForRegex} from "../../../utils/password-validator-for-accented-letters";

@Component({
  selector: 'app-set-new-password',
  templateUrl: './set-new-password.component.html',
  styleUrls: ['./set-new-password.component.css'],
  standalone: true,

  imports: [
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    NgIf,
    MatIconModule,
    NgClass
  ]
})
export class SetNewPasswordComponent {
  passwordForm: FormGroup;
  id: string = '';
  routerEvents: any;
  hide = true;
  isUsed!:PasswordResetTokenCheckModel;


  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private activatedRoute: ActivatedRoute, private router: Router, private authenticationService: AuthenticationService, private http: HttpClient, private dialogRef: MatDialog
  ) {


    this.passwordForm = this.formBuilder.group({
      password: ['', [Validators.required,PasswordValidatorForSpecial(),PasswordValidatorForRegex()]],
      confirmPassword: ['', Validators.required]
    }, {
      validator: ConfirmedValidator('password', 'confirmPassword')

    })
    this.activatedRoute.paramMap.subscribe(
      (param) => {
        const idFromParamMap: string = String(param.get('id'));
        if (idFromParamMap) {
          this.id = idFromParamMap;
        }
      });

    this.userService.getPasswordResetTokenStatus(this.id).subscribe({
      next: data => {
        this.isUsed = data
        },
      error: err => console.log(err)

    })

  }



  sendPassword() {
    const data: NewPasswordModel = {...this.passwordForm.value};
    this.userService.savePassword(data, this.id).subscribe({
      next: value => {
        this.passwordForm.reset();
        this.dialogRef.closeAll();

      },
      error: err => {
        console.log(err)
      },
      complete: () => {

        this.router.navigate(['/login'])
      }
    })
  }


  goToLogin() {
    this.router.navigate(['/login'])
  }
}

