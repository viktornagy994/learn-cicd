import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-completed-register-dialog',
  templateUrl: './completed-register-dialog.component.html',
  styleUrls: ['./completed-register-dialog.component.css']
})
export class CompletedRegisterDialogComponent {

  constructor(private router:Router, private dialogRef:MatDialog) {
  }

  goToHomePage() {
    this.dialogRef.closeAll();
    this.router.navigate(["/home"])
  }
}
