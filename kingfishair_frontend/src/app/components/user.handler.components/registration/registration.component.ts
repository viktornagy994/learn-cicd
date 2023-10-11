import {Component} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {Validators, ReactiveFormsModule, FormGroup, FormBuilder} from '@angular/forms';
import {NgClass, NgIf} from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {Router} from "@angular/router";
import {UserService} from "../../../services/user.service";
import {MatSelectModule} from "@angular/material/select";
import {MatRadioModule} from "@angular/material/radio";
import {MatCardModule} from "@angular/material/card";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {UserRegisterModel} from "../../../models/user-register-model";
import {MatDialog} from "@angular/material/dialog";
import {CompletedRegisterDialogComponent} from "../completed-register-dialog/completed-register-dialog.component";
import {ConfirmedValidator} from "../../../utils/confirmed-validator";
import {validationHandler} from "../../../utils/validationHandler";
import {EmailValidatorForDotAndAt} from "../../../utils/email-validator-for-dot-and-at";
import {NameValidatorForSpace} from "../../../utils/name-validator-for-space";
import {PhoneNumberValidatorForNumericAndPlus} from "../../../utils/phone-number-validator-for-numeric-and plus";
import {PasswordValidatorForRegex} from "../../../utils/password-validator-for-accented-letters";
import {PasswordValidatorForSpecial} from "../../../utils/password-validator-for-special";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule, ReactiveFormsModule, MatIconModule, NgClass, NgIf]
})
export class RegistrationComponent {
  hide = true;
  saveUserForm: FormGroup;


  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private router: Router,
              private dialogRef: MatDialog) {

    this.saveUserForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email, EmailValidatorForDotAndAt()]],
      name: ['', [Validators.required, NameValidatorForSpace()]],
      address: [''],
      phoneNumber: ['', [Validators.required, PhoneNumberValidatorForNumericAndPlus()]],
      password: ['', [Validators.required, PasswordValidatorForSpecial(), PasswordValidatorForRegex()]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6)]]
    }, {
      validator: ConfirmedValidator('password', 'confirmPassword')
    })
  }


  saveRegistration() {
    const data: UserRegisterModel = {...this.saveUserForm.value};
    this.userService.saveUser(data).subscribe({
      next: value => {
        this.dialogRef.closeAll();
        this.dialogRef.open(CompletedRegisterDialogComponent)

      },
      error: err => {
        validationHandler(err, this.saveUserForm);
      },
      complete: () => {

      }
    })
  }
}
