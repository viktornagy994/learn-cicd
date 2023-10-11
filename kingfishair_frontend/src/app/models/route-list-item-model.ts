export interface RouteListItemModel{

  id: number;

  depIata: string;
  depName: string;
  depCity: string;
  depCountry:string;

  arrIata: string;
  arrName: string;
  arrCity :string;
  arrCountry: string;

  distance: number;

  flightTime:number;

  ticketFare: number;

}
