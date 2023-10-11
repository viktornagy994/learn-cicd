import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-contact-us-dialog-component',
  templateUrl: './contact-us-dialog-component.component.html',
  styleUrls: ['./contact-us-dialog-component.component.css']
})
export class ContactUsDialogComponentComponent {

  constructor(private router:Router, private dialogRef:MatDialog) {
  }

  goToHomePage() {
    this.dialogRef.closeAll();
    this.router.navigate(["/home"])
  }
}
