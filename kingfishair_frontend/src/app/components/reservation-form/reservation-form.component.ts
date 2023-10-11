import {Component, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {ReservationsComponent} from "./reservations/reservations.component";
import {ReservationService} from "../../services/reservation.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ReservationDetialsComponent} from "../reservation-detials/reservation-detials.component";
import {MatDialog} from "@angular/material/dialog";
import {ChooseSeatComponent} from "../choose-seat/choose-seat.component";

@Component({
  selector: 'app-reservation-form',
  templateUrl: './reservation-form.component.html',
  styleUrls: ['./reservation-form.component.css']
})
export class ReservationFormComponent implements OnInit {
  reservationForm!: FormGroup;
  reservationFormWithSeats!: any;
  id: number = 0;
  reservationIdForPay:number=0;
  isChosen:boolean=false;


  get reservationsArray(): FormArray {
    return this.reservationForm?.get('reservations') as FormArray;
  }


  constructor(private reservationService: ReservationService, private activatedRoute: ActivatedRoute, private router: Router
    , private dialogRef: MatDialog) {
    this.activatedRoute.paramMap.subscribe(
      (param) => {

        const idFromParamMap: number = Number(param.get('id'));
        if (idFromParamMap) {
          this.id = idFromParamMap;
        }
      })
  }

  ngOnInit(): void {
    this.generateReservationForm();
    /*    this.createCheckboxControls();*/
  }

  private generateReservationForm() {
    this.reservationForm = new FormGroup({
      reservations: new FormArray([
        ReservationsComponent.addNewReservation()
      ])
    });
  }

  public addReservationItem(): void {
    this.reservationsArray?.push(ReservationsComponent.addNewReservation());
  }

  public deleteReservation(index: number) {
    this.reservationsArray?.removeAt(index);
  }

  public submitReservationForm(): void {
    const data = {...this.reservationFormWithSeats}
    this.reservationService.saveReservation(data.reservations, this.id).subscribe({
      next: value => {
        this.reservationForm.reset()
        this.reservationIdForPay=value;
      },
      error: err => {
        console.log(err)
      },
      complete: () => {
        this.goToDetails(this.reservationIdForPay);
      }
    })
  }

  goToDetails(id: number) {
    this.dialogRef.open(ReservationDetialsComponent, {
      data: {
        id: id
      }
    })
  }

  chooseSeat() {
    const dialogReference = this.dialogRef.open(ChooseSeatComponent, {
      data: {
        id: this.id,
        reservationForm: this.reservationForm.value
      }
    })
    dialogReference.afterClosed().subscribe(
      (res) => {
        this.reservationFormWithSeats = res;

        this.isChosen= res == null ? false : true;

      }
    )
  }




}

