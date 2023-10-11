import {Component} from '@angular/core';
import {AdminContactMessageService} from "../../../services/admin-contact-message.service";
import {
  AdminContactMessageFromRegisteredUserModel
} from "../../../models/admin-contact-message-from-registered-user-model";
import {AdminContactMessageFromVisitorModel} from "../../../models/admin-contact-message-from-visitor-model";
import {MatDialog} from "@angular/material/dialog";
import {
  AdminContactUsMessageHandleComponent
} from "../admin-contact-us-message-handle/admin-contact-us-message-handle.component";

@Component({
  selector: 'app-contact-us-messages',
  templateUrl: './admin-contact-us-messages.component.html',
  styleUrls: ['./admin-contact-us-messages.component.css']
})
export class AdminContactUsMessagesComponent {

  unhandledRequestRegistered!: AdminContactMessageFromRegisteredUserModel[];
  handledRequestRegistered!: AdminContactMessageFromRegisteredUserModel[];
  unhandledRequestVisitor!: AdminContactMessageFromVisitorModel[];
  handledRequestVisitor!: AdminContactMessageFromVisitorModel[];
  displayedColumns: string[] = ['email', 'name', 'category', 'message'];
  displayedColumnsUnhandled: string[] = ['email', 'name', 'category', 'message', 'status'];

  constructor(private contacMessageService: AdminContactMessageService, private dialogReference: MatDialog) {

    this.getHandledRequestForRegistered();
    this.getHandledRequestForVisitor();
    this.getUnhandledRequestForRegistered();
    this.getUnhandledRequestForVisitor();

  }


  getUnhandledRequestForRegistered() {
    this.contacMessageService.getUnhandledRequestForRegistered().subscribe({
      next: data => {
        this.unhandledRequestRegistered = data;
      },
      error: err => console.log(err)
    })

  }

  getHandledRequestForRegistered() {
    this.contacMessageService.getHandledRequestForRegistered().subscribe({
      next: data => this.handledRequestRegistered = data,
      error: err => console.log(err)
    })

  }

  getUnhandledRequestForVisitor() {
    this.contacMessageService.getUnhandledRequestForVisitor().subscribe({
      next: data => this.unhandledRequestVisitor = data,
      error: err => console.log(err)
    })

  }

  getHandledRequestForVisitor() {
    this.contacMessageService.getHandledRequestForVisitor().subscribe({
      next: data => this.handledRequestVisitor = data,
      error: err => console.log(err)
    })

  }


  handle(messageId: number, clientEmail: string) {
    const dialogRef = this.dialogReference.open(AdminContactUsMessageHandleComponent, {
      data: {
        id: messageId,
        email: clientEmail,
      }
    })
    dialogRef.afterClosed().subscribe((res) => {

      this.getHandledRequestForRegistered();
      this.getHandledRequestForVisitor();
      this.getUnhandledRequestForRegistered();
      this.getUnhandledRequestForVisitor();

    })

  }
}
