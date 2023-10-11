import {SeatListItemForUserModel} from "./seat-list-item-for-user-model";

export interface ReservationListItemForUserModel{

  departureAirportName:string,
  departureAirportCity:string,
  arrivalAirportName:string,
  arrivalAirportCity:string,
  stDeparture:string,
  stArrival:string,
  seatListItemForUser:SeatListItemForUserModel[],
  id:number,
  checkedinStatus:boolean,
}
