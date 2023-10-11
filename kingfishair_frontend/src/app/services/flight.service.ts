import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {FlightListItemModel} from "../models/flight-list-item-model";
import {FlightItemDetailsModel} from "../models/flight-item-details-model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

const BASE_URL = environment.BASE_URL + '/api/flights';
@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http: HttpClient) {
  }

  getAllFlights() {
    return this.http.get<FlightListItemModel[]>(BASE_URL+'/list-all-flights');
  }

  deleteFlight(flightId: number | undefined) {
    return this.http.delete<FlightListItemModel>(BASE_URL+`/delete/${flightId}`);
  }

  getFlightByID(id: number): Observable<FlightItemDetailsModel> {
    return this.http.get<FlightItemDetailsModel>( BASE_URL+'/flight-details' + '/' + id);
  }
}
