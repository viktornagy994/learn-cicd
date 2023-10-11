import {Component, OnInit} from '@angular/core';
import {formatDate} from "@angular/common";
import {AdminFlightReservationsItemModel} from "../../../models/admin/admin-flight-reservations-item-model";
import {AdminReservationService} from "../../../services/admin-reservation.service";
import {AdminReservationItemModel} from "../../../models/admin/admin-reservation-item-model";

@Component({
  selector: 'app-admin',
  templateUrl: './admin-reservation-list.component.html',
  styleUrls: ['./admin-reservation-list.component.css']
})
export class AdminReservationListComponent implements OnInit {

  flightId: string | null = ""
  flightNr: string | null = "";
  flightDep: string | null = "";
  flightArr: string | null = "";
  flightSTD: string | null = "";
  flightSTA: string | null = "";

  ptrRes: number = -1;
  ptrSeat: number = -1;

  reservations: AdminFlightReservationsItemModel[] = [];
  seats: AdminReservationItemModel[] = [];
  haveSeats: boolean = false;

  constructor(private resService: AdminReservationService) {
  }


  ngOnInit() {

    this.flightId = localStorage.getItem("flight-id-for-reservations");
    localStorage.setItem("flight-id-for-reservations","");

    this.flightNr = localStorage.getItem("flight-flightnr");
    localStorage.setItem("flight-flightnr","");

    this.flightDep = localStorage.getItem("flight-depairport");
    localStorage.setItem("flight-depairport","");

    this.flightArr = localStorage.getItem("flight-arrairport");
    localStorage.setItem("flight-arrairport","");

    this.flightSTD = localStorage.getItem("flight-std");
    localStorage.setItem("flight-std","");

    this.flightSTA = localStorage.getItem("flight-sta");
    localStorage.setItem("flight-sta","");


    if (this.flightId != "") {
      this.resService.getAdminFlightReservations(this.flightId).subscribe({
        next: value => {
          this.reservations = value;
        },
        error: err => {
        },
        complete: () => {
        }
      });
    }


  }


  goToSeats(i: number) {
    this.ptrRes = i;
    this.seats = this.reservations[i].reservations;
    this.haveSeats = this.seats.length > 0;
  }

  deleteSeat(i: number) {
    this.ptrSeat = i;
    this.resService.getAdminDeleteSeat(this.seats[i].id).subscribe({
      next: value => {
      },
      error: err => {
      },
      complete: () => {
      }
    });
    this.reservations[this.ptrRes].reservations.splice(this.ptrSeat,1);
    this.goToSeats(this.ptrRes);
  }


}
