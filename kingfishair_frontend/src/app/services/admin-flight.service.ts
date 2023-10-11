import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RouteListForFlightFormListItemModel} from "../models/route-list-for-flight-form-list-item-model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {CreateRouteModel} from "../models/create-route.model";
import {PlaneListForFlightFormListItemModel} from "../models/plane-list-for-flight-form-list-item-model";
import {AdminFlightListOrderFilterModel} from "../models/admin-flight-list-order-filter-model";
import {AdminFlightListItemModel} from "../models/admin-flight-list-item-model";
import {CreateFlightModel} from "../models/create-flight-model";

const BASE_URL = environment.BASE_URL + '/api/flights';


@Injectable({
  providedIn: 'root'
})
export class AdminFlightService {

  constructor(private http: HttpClient) {
  }


  getRouteListForFlightFormListItem(): Observable<RouteListForFlightFormListItemModel[]> {
    return this.http.get<RouteListForFlightFormListItemModel[]>(BASE_URL + '/get-route-list-for-flight-form');
  }

  getPlaneListForFlightFormListItem(): Observable<PlaneListForFlightFormListItemModel[]> {
    return this.http.get<PlaneListForFlightFormListItemModel[]>(BASE_URL + '/get-plane-list-for-flight-form');
  }


  createFlight(data: CreateFlightModel) {
    return this.http.post(BASE_URL + "/create-flight", data);
  }

  getFlightListForAdmin(data: AdminFlightListOrderFilterModel): Observable<AdminFlightListItemModel[]> {
    return this.http.post<AdminFlightListItemModel[]>(BASE_URL + '/get-flight-list-for-admin', data);
  }


}
