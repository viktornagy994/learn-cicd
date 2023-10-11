import {Component, OnInit} from '@angular/core';
import {RouteListItemModel} from "../../../models/route-list-item-model";
import {AdminRouteService} from "../../../services/admin-route.service";
import {AdminRouteListOrderFilterModel} from "../../../models/admin-route-list-order-filter-model";

@Component({
  selector: 'app-admin-route-list',
  templateUrl: './admin-route-list.component.html',
  styleUrls: ['./admin-route-list.component.css']
})
export class AdminRouteListComponent implements OnInit {

  LIST_ORDER_DEP_AIRPORT: number = 1;
  LIST_ORDER_ARR_AIRPORT: number = 2;
  listOrderFilter: AdminRouteListOrderFilterModel = {
    order: this.LIST_ORDER_DEP_AIRPORT,
    filterDepAirportName: '',
    filterArrAirportName: '',
    itemPerPage: 8,
    page: 0
  };

  routes!: RouteListItemModel[];
  routes_temp!: RouteListItemModel[];

  pageNextCalled: boolean = false;


  constructor(private adminRouteService: AdminRouteService) {
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

    this.adminRouteService.getRouteListForAdmin(this.listOrderFilter).subscribe({
      next: value => {
        this.routes_temp = value;
        if (this.routes_temp.length > 0) {
          this.routes = value;
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


}
