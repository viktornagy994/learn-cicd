import { Component } from '@angular/core';
import {FlightListItemModel} from "../../models/flight-list-item-model";
import {FlightService} from "../../services/flight.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {FlightDetailsComponent} from "../flight-details/flight-details.component";

@Component({
  selector: 'app-visible-flight-list-component',
  templateUrl: './visible-flight-list.component.html',
  styleUrls: ['./visible-flight-list.component.css']
})
export class VisibleFlightListComponent {
  flights: FlightListItemModel[] = [];
  isFetching = false;
  displayedColumns: string[] = ['id', 'flightNumber', 'departureAirport', 'arrivalAirport', 'stDeparture', 'stArrival', 'detailsButton'];

  constructor(private flightService: FlightService,
              private router: Router,
              private dialogRef: MatDialog) {
  }

  ngOnInit() {
    this.isFetching = true;
    this.flightService.getAllFlights().subscribe(response => {
      this.isFetching = false;
      this.flights = response;

    })
  }

  goToDetails(id: number) {
    this.dialogRef.open(FlightDetailsComponent, {
      data :{
        id:id
      }
    })
    //this.router.navigate(['flight-details', id]);
  }
}
