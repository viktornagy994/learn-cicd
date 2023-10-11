export interface AdminFlightListItemModel {
  id: number;

  flightNumber: string;

  depIata: string;
  depName: string;
  depCity: string;
  depCountry:string;

  arrIata: string;
  arrName: string;
  arrCity :string;
  arrCountry: string;

  stDeparture: Date;
  stArrival: Date;

  planeRegistrationNr: string;

}
