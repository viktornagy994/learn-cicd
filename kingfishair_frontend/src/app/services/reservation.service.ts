import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {AirportOptionForReservationModel} from "../models/airport-option-for-reservation-model";
import {FlightOptionForReservationFormModel} from "../models/flight-option-for-reservation-form-model";
import {AirportIdsForReservationModel} from "../models/airport-ids-for-reservation-model";
import {ReservationFormDataModel} from "../models/reservation-form-data-model";
import {NewReservationFormModel} from "../models/new-reservation-form-model";



import {ReservationListItemForUserModel} from "../models/reservation-list-item-for-user-model";
import {ReservationDetailsForUserModel} from "../models/reservation-details-for-user-model";
const BASE_URL = environment.BASE_URL + '/api/reservations';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http:HttpClient) { }

  getMyFutureReservations():Observable<ReservationListItemForUserModel[]>{
    return this.http.get<ReservationListItemForUserModel[]>(BASE_URL+"/my-reservation-future")
  }

  getMyPastReservations():Observable<ReservationListItemForUserModel[]> {
    return this.http.get<ReservationListItemForUserModel[]>(BASE_URL + "/my-reservation-past")
  }
  getInitialFormData(): Observable<AirportOptionForReservationModel[]> {
    return this.http.get<AirportOptionForReservationModel[]>(BASE_URL + '/get-airport-list-for-reservation-form')
  }

  getFlightListForReservationForm(data: AirportIdsForReservationModel): Observable<FlightOptionForReservationFormModel[]> {
    return this.http.post<FlightOptionForReservationFormModel[]>(BASE_URL + '/get-flight-list-for-reservation-form', data)
  }

  getReservationFormData(): Observable<ReservationFormDataModel> {
    return this.http.get<ReservationFormDataModel>(BASE_URL + '/get-reservation-form-data')
  }

  saveReservation(data: NewReservationFormModel[],id:number): Observable<any> {
    return this.http.post(BASE_URL+'/'+id, data)
  }
  payReservation(data:number,id:number): Observable<any> {
    return this.http.put(BASE_URL+'/pay/'+id,data)
  }
  getReservationDetailsbyId(id:number):Observable<ReservationDetailsForUserModel>{
    return this.http.get<ReservationDetailsForUserModel>(BASE_URL+'/reservation-details'+ '/'+ id);
  }

  getReservationSeatsbyId(id:number):Observable<number[]>{
    return this.http.get<number[]>(BASE_URL+'/reservation-seats'+ '/'+ id);
  }


  getMyPendingReservations():Observable<ReservationListItemForUserModel[]>{
    return this.http.get<ReservationListItemForUserModel[]>(BASE_URL+"/my-reservation-pending")
  }

  getQRCode(id:number):Observable<Blob>{
    return this.http.get(BASE_URL+"/barcodes/zxing/qrcode/"+id, { responseType: 'blob' })
  }

  getCheckinStatus(id:number):Observable<boolean>{
    return this.http.get<boolean>(BASE_URL+"/checkin-status/"+id)
  }

  getTicketPDF(reservationId:number, seatId:number):Observable<Blob>{

    const headers=new HttpHeaders({'Content-Type': 'application/json', 'responseType':'blob'})

    const params = new HttpParams().set('reservationId',reservationId).set('seatId',seatId);


    return this.http.get<Blob>(BASE_URL+"/get-ticket" , {headers:headers,responseType:'blob'as 'json', params:params})
  }

}
