import {Component, Inject, OnDestroy} from '@angular/core';
import {ReservationService} from "../../services/reservation.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {interval, Subscription} from "rxjs";

@Component({
  selector: 'app-qr-code',
  templateUrl: './qr-code.component.html',
  styleUrls: ['./qr-code.component.css']
})
export class QRCodeComponent implements OnDestroy{

  QRCode!:any;
  reservationId!:number;
  checkedinStatus=false;
  subscription!: Subscription;
  source = interval(3000);


  constructor( private activatedRoute: ActivatedRoute, private reservationService: ReservationService,
               @Inject(MAT_DIALOG_DATA) private data:any, private router: Router,
               private dialogReference: MatDialogRef<QRCodeComponent>) {


    this.activatedRoute.paramMap.subscribe(
      (param) => {
        const idFromParamMap: number = Number(data.id);
        if (idFromParamMap) {
          this.reservationId = idFromParamMap;
          this.reservationService.getQRCode(this.reservationId).subscribe({
            next: data => {this.QRCode = data;
            this.createImageFromBlob(data)}
          });

        }
      }
    )
    this.subscription = this.source.subscribe(val => this.getCheckinStatus());


  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.QRCode = reader.result;

    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }





  getCheckinStatus(){
    if (this.checkedinStatus==false) {
      this.reservationService.getCheckinStatus(this.reservationId).subscribe({
          next: data => {
            this.checkedinStatus = data;

          }
        }
      )
    } else this.subscription.unsubscribe();
  }


  closeDialog() {
    this.dialogReference.close(this.reservationId)

  }

  ngOnDestroy(): void {

    this.subscription.unsubscribe();
    this.dialogReference.close(this.reservationId);
  }
}
