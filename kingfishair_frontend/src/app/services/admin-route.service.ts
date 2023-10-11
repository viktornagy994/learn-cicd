import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AirportListForRouteFormListitemModel} from "../models/airport-list-for-route-form-listitem.model";
import {CreateRouteModel} from "../models/create-route.model";
import {environment} from "../../environments/environment";
import {RouteListItemModel} from "../models/route-list-item-model";
import {AdminRouteListOrderFilterModel} from "../models/admin-route-list-order-filter-model";

const BASE_URL = environment.BASE_URL + '/api/routes';

@Injectable({
  providedIn: 'root'
})
export class AdminRouteService {

  constructor(private http: HttpClient) {
  }


  getAirportListForRouteForm(): Observable<AirportListForRouteFormListitemModel[]> {
    return this.http.get<AirportListForRouteFormListitemModel[]>(BASE_URL + '/get-airport-list-for-route-form');
  }


  createRoute(data: CreateRouteModel) {
    return this.http.post(BASE_URL + "/create-route", data);
  }

  getRouteListForAdmin(data: AdminRouteListOrderFilterModel): Observable<RouteListItemModel[]> {
    return this.http.post<RouteListItemModel[]>(BASE_URL + '/get-route-list-for-admin', data);
  }


}
