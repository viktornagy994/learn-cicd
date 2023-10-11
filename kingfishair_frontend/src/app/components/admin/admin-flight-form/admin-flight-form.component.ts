import {Component, OnInit} from '@angular/core';
import {RouteListForFlightFormListItemModel} from "../../../models/route-list-for-flight-form-list-item-model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {AdminFlightService} from "../../../services/admin-flight.service";
import {PlaneListForFlightFormListItemModel} from "../../../models/plane-list-for-flight-form-list-item-model";

@Component({
  selector: 'app-admin-flight-form',
  templateUrl: './admin-flight-form.component.html',
  styleUrls: ['./admin-flight-form.component.css']
})
export class AdminFlightFormComponent implements OnInit {

  routeList: RouteListForFlightFormListItemModel[] = [];
  planeList: PlaneListForFlightFormListItemModel[] = [];


  formFlight!: FormGroup;

  constructor(private adminFlightService: AdminFlightService,
              private formBuilder: FormBuilder,
              private router: Router) {

    this.formFlight = formBuilder.group({
      flightNumber: [''],
      routeId: [''],
      stDeparture: [''],
      stArrival: [''],
      planeId: ['']
    });

  }


  ngOnInit() {


    this.adminFlightService.getRouteListForFlightFormListItem().subscribe({
      next: value => {
        this.routeList = value;

      },
      error: err => {
        console.log('error: ' + err);
      },
      complete: () => {

      }
    });

    this.adminFlightService.getPlaneListForFlightFormListItem().subscribe({
      next: value => {
        this.planeList = value;

      },
      error: err => {

      },
      complete: () => {

      }
    });


  }


  submitFormFlight() {
    const data = this.formFlight.value;

    this.adminFlightService.createFlight(data).subscribe({
      next: value => {
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.router.navigate(["/admin-flight-list"]);
      }

    });


  }
}
