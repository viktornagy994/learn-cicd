import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {ReservationDetailsForUserModel} from "../../models/reservation-details-for-user-model";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {ReservationService} from "../../services/reservation.service";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import { IPayPalConfig, ICreateOrderRequest } from 'ngx-paypal';


@Component({
  selector: 'app-reservation-detials',
  templateUrl: './reservation-detials.component.html',
  styleUrls: ['./reservation-detials.component.css']
})
export class ReservationDetialsComponent implements OnInit{

  public payPalConfig?: IPayPalConfig;



  id: number = 0;
  reservation: ReservationDetailsForUserModel = {
    id:0,
    flightNumber: '',
    customerName: '',
    numberOfSeats: 0,
    totalFare: 0,
  };

  ispaid:boolean=false;

  constructor(private activatedRoute: ActivatedRoute, private reservationService: ReservationService,
              @Inject(MAT_DIALOG_DATA) private data:any, private router: Router,
              private dialogReference: MatDialog) {
    this.activatedRoute.paramMap.subscribe(
      (param: ParamMap)=>{
        const idFromParamMap: number = Number(data.id);
        if (idFromParamMap){
          this.id= idFromParamMap;
          this.reservationService.getReservationDetailsbyId(this.id).subscribe({
            next: data => this.reservation = data
          })
        }
      }
    )
    this.checkPaymentstatus();
  }

  payLater() {
    this.dialogReference.closeAll();
    this.router.navigate(['/my-profile'])
  }

  payNow() {
    const data = this.id;
    this.reservationService.payReservation(data,this.id).subscribe({
      next: value => {   },
      error: err => {
        console.log(err)
      },
      complete: () => {
        this.dialogReference.closeAll();
        this.router.navigate(['/my-profile'])

      }
    })

  }

  private initConfig(): void {
    this.payPalConfig = {
      currency: 'EUR',
      clientId: 'sb',
      createOrderOnClient: (data) => <ICreateOrderRequest>{
        intent: 'CAPTURE',
        purchase_units: [
          {
            amount: {
              currency_code: 'EUR',
              value: this.reservation.totalFare.toString(),
              breakdown: {
                item_total: {
                  currency_code: 'EUR',
                  value: this.reservation.totalFare.toString()
                }
              }
            },
            items: [
              {
                name: 'KingfishAir Air Fare',
                quantity: '1',
                category: 'PHYSICAL_GOODS',
                unit_amount: {
                  currency_code: 'EUR',
                  value: this.reservation.totalFare.toString(),
                },
              }
            ]
          }
        ]
      },
      advanced: {
        commit: 'true'
      },
      style: {
        label: 'paypal',
        layout: 'vertical'
      },
      onApprove: (data, actions) => {


        // @ts-ignore
        actions.order.get().then(details => {

        });
      },
      onClientAuthorization: (data) => {
        const data2 = this.id;
        this.reservationService.payReservation(data2,this.id).subscribe({
          next: value => {   },
          error: err => {
            console.log(err)
          },
          complete: () => {
            this.ispaid=true;


          }
        })

      },
      onCancel: (data, actions) => {

      },
      onError: err => {
        console.log('OnError', err);
      },
      onClick: (data, actions) => {

      },
    };
  }


  checkPaymentstatus(){
    if (this.ispaid){
      return true;
    }
else return false;

  }


  ngOnInit(): void {
    this.initConfig();

  }


  goToMyProfile() {
    this.dialogReference.closeAll();
    this.router.navigate(['my-profile']);
  }


}
