import {Component, Inject} from '@angular/core';
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {NgForOf, NgStyle} from "@angular/common";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {ReservationService} from "../../services/reservation.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import {SeatNumbers} from "../../models/seat-numbers";
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";

@Component({
  selector: 'app-choose-seat',
  templateUrl: './choose-seat.component.html',
  styleUrls: ['./choose-seat.component.css'],
  standalone: true,

  imports: [
    MatTableModule,
    MatIconModule,
    NgForOf,
    MatInputModule,
    NgStyle,
    MatButtonModule,
    MatCardModule
  ]
})
export class ChooseSeatComponent {
  seats: SeatNumbers[] = [];
  redSeats: SeatNumbers[] = [];
  reservedSeats: number[] | any[] = [];
  reservationForm!: any;





  id: number = 0;

  constructor(private activatedRoute: ActivatedRoute, private reservationService: ReservationService,
              @Inject(MAT_DIALOG_DATA) private data: any, private router: Router,
              private dialogReference: MatDialogRef<ChooseSeatComponent>) {

    for (let i = 0; i < 12; i++) {
      let seat: SeatNumbers = {
        seatNumber: i + 1,
        seatStatus: 'free'
      }
      this.seats.push(seat)
    }

    this.activatedRoute.paramMap.subscribe(
      (param: ParamMap) => {
        const idFromParamMap: number = Number(data.id);
        const formFromParamMap:any = data.reservationForm;
        if (idFromParamMap) {
          this.id = idFromParamMap;
          this.reservationService.getReservationSeatsbyId(this.id).subscribe({
              next: data => {
                this.reservedSeats = data
                for (let i = 0; i < this.reservedSeats.length; i++) {
                  let seat: SeatNumbers = {
                    seatNumber: this.reservedSeats[i].seatNumber,
                    seatStatus: 'free'
                  }
                  this.redSeats.push(seat)
                }
                for (let i = 0; i < this.redSeats.length; i++) {
                  for (let j = 0; j < this.seats.length; j++) {
                    if (this.redSeats[i].seatNumber == this.seats[j].seatNumber) {
                      this.seats[j].seatStatus = 'reserved'
                    }
                  }
                }
              }
            }
          )
        }
        if (formFromParamMap){
          this.reservationForm=formFromParamMap;

        }
        for (let i = 0; i < this.reservationForm.reservations.length; i++) {
          for (let j = 0; j < this.seats.length; j++) {
            if (this.reservationForm.reservations[i].seatNumber == this.seats[j].seatNumber) {
              this.seats[j].seatStatus = 'underReservation'
            }
          }
        }
      }
    )
  }

  checkSeatStatus(seat: SeatNumbers) {

    if (seat.seatStatus == 'reserved') {
      return '#E78282FF'
    }
    else if (seat.seatStatus == 'underReservation') {
      return 'darkcyan'
    } else return '#DCECD7'
  }

  protected readonly NgStyle = NgStyle;

  getSeats(seat: SeatNumbers) {
    for (let i = 0; i < this.reservationForm.reservations.length; i++) {
      if (this.reservationForm.reservations[i].seatNumber == 0 && seat.seatStatus == 'free'){
        seat.seatStatus = 'underReservation'
        this.reservationForm.reservations[i].seatNumber = seat.seatNumber;
        break;
      }
      if (this.reservationForm.reservations[i].seatNumber == seat.seatNumber && seat.seatStatus == 'underReservation'){
        seat.seatStatus = 'free'
        this.reservationForm.reservations[i].seatNumber = 0;
        break;
      }

    }


    }


  saveSeat() {
this.dialogReference.close(this.reservationForm)
  }
}

