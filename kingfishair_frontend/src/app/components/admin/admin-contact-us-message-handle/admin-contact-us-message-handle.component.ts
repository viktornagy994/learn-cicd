import {Component, Inject} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ReservationService} from "../../../services/reservation.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AdminContactUsMessagesComponent} from "../admin-contact-us-messages/admin-contact-us-messages.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EmailValidatorForDotAndAt} from "../../../utils/email-validator-for-dot-and-at";
import {NameValidatorForSpace} from "../../../utils/name-validator-for-space";
import {PhoneNumberValidatorForNumericAndPlus} from "../../../utils/phone-number-validator-for-numeric-and plus";
import {PasswordValidatorForSpecial} from "../../../utils/password-validator-for-special";
import {PasswordValidatorForRegex} from "../../../utils/password-validator-for-accented-letters";
import {ConfirmedValidator} from "../../../utils/confirmed-validator";
import {AdminContactMessageService} from "../../../services/admin-contact-message.service";
import {AdminContactUsMessageReplyModel} from "../../../models/admin-contact-us-message-reply-model";
import {
  CompletedRegisterDialogComponent
} from "../../user.handler.components/completed-register-dialog/completed-register-dialog.component";
import {validationHandler} from "../../../utils/validationHandler";

@Component({
  selector: 'app-admin-contact-us-message-handle',
  templateUrl: './admin-contact-us-message-handle.component.html',
  styleUrls: ['./admin-contact-us-message-handle.component.css']
})
export class AdminContactUsMessageHandleComponent {

  messageId!: number;
  clientEmail!: string;
  saveReplyForm: FormGroup;


  constructor(private activatedRoute: ActivatedRoute, private reservationService: ReservationService,
              @Inject(MAT_DIALOG_DATA) private data: any, private router: Router,
              private dialogReference: MatDialogRef<AdminContactUsMessagesComponent>, private formBuilder: FormBuilder,
              private adminContactMessageService: AdminContactMessageService) {

    const idFromParamMap: number = Number(data.id);
    const emailFromParamMap: string = String(data.email);
    if (idFromParamMap) {
      this.messageId = idFromParamMap;
    }
    if (emailFromParamMap) {
      this.clientEmail = emailFromParamMap;
    }

    this.saveReplyForm = this.formBuilder.group({
      reply: [''],

    })

  }


  closeDialog() {
    const data: AdminContactUsMessageReplyModel = {
      clientEmail: this.clientEmail,
      reply: this.saveReplyForm.value.reply,
      messageId: this.messageId
    }
    this.adminContactMessageService.saveReply(data).subscribe({
      next: value => {

      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.dialogReference.close()
      }

    })
  }
}
