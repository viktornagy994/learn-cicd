package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.Seat;

public class ReservedSeats {
    private Long seatNumber;

    public ReservedSeats(Seat seat) {
        this.seatNumber = seat.getSeatNumber();
    }

    public ReservedSeats() {
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }
}
