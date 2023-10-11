import {Component, Inject, OnInit} from '@angular/core';
import {GoogleMap, GoogleMapsModule} from "@angular/google-maps";
import {BrowserModule} from "@angular/platform-browser";
import {ActivatedRoute, RouterOutlet} from "@angular/router";
import {GoogleSigninButtonModule} from "@abacritt/angularx-social-login";
import {FlightService} from "../../services/flight.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";


@Component({
  selector: 'app-arrival-city-map-for-accomodation',
  templateUrl: './arrival-city-map-for-accomodation.component.html',
  styleUrls: ['./arrival-city-map-for-accomodation.component.css'],
  standalone:true,
  imports: [GoogleMapsModule, GoogleSigninButtonModule,

  ]
})
export class ArrivalCityMapForAccomodationComponent implements OnInit{

zoom = 11;
  initialCoordinates = {
    lat: 0,
    lng: 0,
  };
  mapConfigurations = {
    disableDefaultI: true,
    fullscreenControl: true,
    zoomControl: true,
    mapTypeId: "roadmap",
    scrollwheel: true,
    disableDoubleClickZoom: true,
  };
  display: any;
  constructor(private route: ActivatedRoute,
              @Inject(MAT_DIALOG_DATA) private data: any) {
    this.route.paramMap.subscribe(
      (param) => {
          this.initialCoordinates.lat= Number(this.data.myLat);
        this.initialCoordinates.lng=Number(this.data.myLong);
      }
    )
  }

  ngOnInit(): void {


  }

  moveMap(event: google.maps.MapMouseEvent) {
    if (event.latLng != null) this.initialCoordinates = (event.latLng.toJSON());
  }

  move(event: google.maps.MapMouseEvent) {
    if (event.latLng != null) this.display = event.latLng.toJSON();
  }

}
