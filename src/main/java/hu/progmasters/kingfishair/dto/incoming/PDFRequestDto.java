package hu.progmasters.kingfishair.dto.incoming;

public class PDFRequestDto {

    private Long reservationId;

    private Long seatId;

    public PDFRequestDto(Long reservationId, Long seatId) {
        this.reservationId = reservationId;
        this.seatId = seatId;
    }
    public PDFRequestDto() {
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }


}
