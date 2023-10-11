import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {DatePipe, NgClass, NgForOf} from "@angular/common";
import {MatSelectModule} from "@angular/material/select";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatRadioModule} from "@angular/material/radio";
import {MatButtonModule} from "@angular/material/button";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatMenuModule} from "@angular/material/menu";
import {MatListModule} from "@angular/material/list";
import {MatSidenavModule} from "@angular/material/sidenav";
import {RouterOutlet} from "@angular/router";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatTabsModule} from "@angular/material/tabs";
import {MatTableModule} from "@angular/material/table";
import {UserService} from "../../services/user.service";
import {MessageCategoryOptionsModel} from "../../models/message-category-options-model";
import {ContactMessageFormDataModel} from "../../models/contact-message-form-data-model";
import {EmailValidatorForDotAndAt} from "../../utils/email-validator-for-dot-and-at";
import {NameValidatorForSpace} from "../../utils/name-validator-for-space";
import {PhoneNumberValidatorForNumericAndPlus} from "../../utils/phone-number-validator-for-numeric-and plus";
import {PasswordValidatorForSpecial} from "../../utils/password-validator-for-special";
import {PasswordValidatorForRegex} from "../../utils/password-validator-for-accented-letters";
import {ConfirmedValidator} from "../../utils/confirmed-validator";
import {UserRegisterModel} from "../../models/user-register-model";
import {
  CompletedRegisterDialogComponent
} from "../user.handler.components/completed-register-dialog/completed-register-dialog.component";
import {validationHandler} from "../../utils/validationHandler";
import {ContactMessageCommandModel} from "../../models/contact-message-command-model";
import {MatDialog} from "@angular/material/dialog";
import {ContactUsDialogComponentComponent} from "../contact-us-dialog-component/contact-us-dialog-component.component";

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css'],
})
export class ContactUsComponent implements OnInit {

  form!: FormGroup;
  categories!: MessageCategoryOptionsModel[];

  ngOnInit(): void {
    this.userService.getContactFormData().subscribe((contactMessageFormModel:ContactMessageFormDataModel)=>{
      this.categories=contactMessageFormModel.categories;
    })
  }

  constructor(private formBuilder: FormBuilder, private userService:UserService, private dialogref:MatDialog) {
    this.form=this.formBuilder.group({
      clientName: ['', Validators.required],
      clientEmail: ['',Validators.required ],
      message: ['',Validators.required],
      category: ['',Validators.required ]
    })
  }

  send(): void {
    const data: ContactMessageCommandModel = {...this.form.value};
    this.userService.saveMessage(data).subscribe({
      next: value => {

      },
      error: err => {

      },
      complete: () => {
        this.dialogref.open(ContactUsDialogComponentComponent)
      }
    })
  }


}
