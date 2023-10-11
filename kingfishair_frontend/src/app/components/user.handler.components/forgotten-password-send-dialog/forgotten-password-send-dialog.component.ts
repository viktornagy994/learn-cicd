import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-forgotten-password-send-dialog',
  templateUrl: './forgotten-password-send-dialog.component.html',
  styleUrls: ['./forgotten-password-send-dialog.component.css']
})
export class ForgottenPasswordSendDialogComponent {

  constructor(private router:Router, private dialogRef:MatDialog) {
  }

  goToHomePage() {
    this.dialogRef.closeAll();
    this.router.navigate(["/home"])
  }
  }

