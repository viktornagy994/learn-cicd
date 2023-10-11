package hu.progmasters.kingfishair.dto.incoming;

import java.util.List;

public class NewReservationCommandArray {

    List<NewReservationCommand> reservations;

    public NewReservationCommandArray(List<NewReservationCommand> reservations) {
        this.reservations = reservations;
    }

    public NewReservationCommandArray() {
    }

    public List<NewReservationCommand> getReservations() {
        return reservations;
    }

    public void setReservations(List<NewReservationCommand> reservations) {
        this.reservations = reservations;
    }
}
