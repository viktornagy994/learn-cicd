import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {AdminFlightReservationsItemModel} from "../models/admin/admin-flight-reservations-item-model";


const BASE_URL = environment.BASE_URL + '/api/reservations';


@Injectable({
  providedIn: 'root'
})
export class AdminReservationService {

  constructor(private http: HttpClient) {
  }


  getAdminFlightReservations(flightId: string | null): Observable<AdminFlightReservationsItemModel[]> {
    return this.http.get<AdminFlightReservationsItemModel[]>(BASE_URL + "/admin-get-flight-reservations" + "/" + flightId)
  }

  getAdminDeleteSeat(seatId: number): Observable<any> {
    return this.http.get<any>(BASE_URL + "/admin-delete-seat" + "/" + seatId)
  }




}
