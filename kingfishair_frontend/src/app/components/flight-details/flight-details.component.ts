import { Component, Inject, OnInit } from '@angular/core';
import { FlightItemDetailsModel } from "../../models/flight-item-details-model";
import { FlightService } from "../../services/flight.service";
import { ActivatedRoute } from "@angular/router";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";

@Component({
  selector: 'app-flight-details',
  templateUrl: './flight-details.component.html',
  styleUrls: ['./flight-details.component.css']
})
export class FlightDetailsComponent implements OnInit {
  id: number = 0;
  flight: FlightItemDetailsModel = {
    id: 0,
    stDeparture: new Date(),
    flightNumber: '',
    departureAirport: '',
    arrivalAirport: '',
    flightTime: 0,
    imageUrl: '',
    planeName: '',
  };

  constructor(private route: ActivatedRoute,
              private flightService: FlightService,
              @Inject(MAT_DIALOG_DATA) private data: any) {
    this.route.paramMap.subscribe(
      (param) => {
        const idFromParamMap: number = Number(data.id);
        if (idFromParamMap) {
          this.id = idFromParamMap;
          this.flightService.getFlightByID(this.id).subscribe({
            next: data => this.flight = data
          })
        }
      }
    )
  }

  ngOnInit(): void {
  }
}
