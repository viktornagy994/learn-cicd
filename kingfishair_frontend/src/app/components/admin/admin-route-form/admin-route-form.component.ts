import {Component, OnInit} from '@angular/core';
import {AirportListForRouteFormListitemModel} from "../../../models/airport-list-for-route-form-listitem.model";
import {AdminRouteService} from "../../../services/admin-route.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-admin-route-form',
  templateUrl: './admin-route-form.component.html',
  styleUrls: ['./admin-route-form.component.css']
})
export class AdminRouteFormComponent implements OnInit {

  airportList: AirportListForRouteFormListitemModel[] = [];

  formRoute!: FormGroup;

  constructor(private adminRouteService: AdminRouteService,
              private formBuilder: FormBuilder,
              private router: Router) {

    this.formRoute = formBuilder.group({
      departureAirportId: [''],
      arrivalAirportId: [''],
      distance: [''],
      flightTime: [''],
      ticketFare: [''],
    });


  }


  ngOnInit() {

    this.adminRouteService.getAirportListForRouteForm().subscribe({
      next: value => {
        this.airportList = value;
      },
      error: err => {
        console.log('error: ' + err);
      },
      complete: () => {

      }
    });


  }


  submitFormRoute() {

    const data = this.formRoute.value;

    this.adminRouteService.createRoute(data).subscribe({
      next: value => {
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        this.router.navigate(["/admin-route-list"]);
      }
    });


  }


}
