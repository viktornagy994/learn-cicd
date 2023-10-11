import {Component, Inject, LOCALE_ID, OnInit} from '@angular/core';
import {AdminFlightListOrderFilterModel} from "../../../models/admin-flight-list-order-filter-model";
import {AdminFlightListItemModel} from "../../../models/admin-flight-list-item-model";
import {AdminFlightService} from "../../../services/admin-flight.service";
import {Router} from "@angular/router";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-admin-flight-list',
  templateUrl: './admin-flight-list.component.html',
  styleUrls: ['./admin-flight-list.component.css']
})
export class AdminFlightListComponent implements OnInit {

  LIST_ORDER_FLIGHT_NR: number = 1;
  LIST_ORDER_DEP_AIRPORT: number = 2;
  LIST_ORDER_ARR_AIRPORT: number = 3;
  listOrderFilter: AdminFlightListOrderFilterModel = {
    order: this.LIST_ORDER_FLIGHT_NR,
    filterFlightNr: '',
    filterDepAirportName: '',
    filterArrAirportName: '',
    itemPerPage: 12,
    page: 0
  };

  flights!: AdminFlightListItemModel[];
  flights_temp!: AdminFlightListItemModel[];

  pageNextCalled: boolean = false;


  constructor(private adminFlightService: AdminFlightService,
              private router: Router,
              @Inject(LOCALE_ID) private locale: string) {
  }


  ngOnInit() {

    this.getListFromBackend();

  }


  changeListOrder(order: number) {
    this.listOrderFilter.order = order;
    this.listOrderFilter.page = 0;
    this.getListFromBackend();
  }

  pagePrior() {
    if (this.listOrderFilter.page > 0) {
      this.listOrderFilter.page--;
      this.getListFromBackend();
    }
  }

  pageNext() {
    this.pageNextCalled = true;
    this.listOrderFilter.page++;
    this.getListFromBackend();
  }

  getListFromBackend() {

    this.adminFlightService.getFlightListForAdmin(this.listOrderFilter).subscribe({
      next: value => {
        this.flights_temp = value;
        if (this.flights_temp.length > 0) {
          this.flights = value;
        } else {
          if (this.pageNextCalled) {
            this.listOrderFilter.page--;
          }
        }
        this.pageNextCalled = false;
      },
      error: err => {
        console.log('error: ' + err);
      },
      complete: () => {
      }
    });

  }

  onKeyFlightNrFilter(event: any) { // without type info
    this.listOrderFilter.filterFlightNr = event.target.value;  // csak a most lenyomott billentyű: event.key
    this.listOrderFilter.page = 0;
    this.getListFromBackend();
  }

  onKeyDepAirportFilter(event: any) { // without type info
    this.listOrderFilter.filterDepAirportName = event.target.value;  // csak a most lenyomott billentyű: event.key
    this.listOrderFilter.page = 0;
    this.getListFromBackend();
  }

  onKeyArrAirportFilter(event: any) {
    this.listOrderFilter.filterArrAirportName = event.target.value;
    this.listOrderFilter.page = 0;
    this.getListFromBackend();
  }


  goToReservations(i: number) {
    localStorage.setItem("flight-id-for-reservations","" + this.flights[i].id);
    localStorage.setItem("flight-flightnr",this.flights[i].flightNumber);
    localStorage.setItem("flight-depairport",this.flights[i].depCity + ", " + this.flights[i].depName);
    localStorage.setItem("flight-arrairport",this.flights[i].arrCity + ", " + this.flights[i].arrName);
    localStorage.setItem("flight-std",formatDate(this.flights[i].stDeparture,'yyyy-MM-dd HH:mm',this.locale));
    localStorage.setItem("flight-sta",formatDate(this.flights[i].stArrival,'yyyy-MM-dd HH:mm',this.locale));
    this.router.navigate(["/admin-reservation-list"]);
  }

}
