import {AdminReservationItemModel} from "./admin-reservation-item-model";

export interface AdminFlightReservationsItemModel {
  userName: string;
  userEmail: string;
  userPhone: string;
  userAddress: string;
  reservations: AdminReservationItemModel[];
}
