package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.Reservation;

public class ReservationDetailsForUser {
    private Long reservationId;
    private String flightNumber;

    private String customerName;

    private Integer numberOfSeats;

    private Long totalFare;

    public ReservationDetailsForUser(Reservation reservation) {
        this.reservationId = reservation.getId();
        this.flightNumber = reservation.getFlight().getFlightNumber();
        this.customerName = reservation.getCustomer().getName();
        this.numberOfSeats = reservation.getSeatList().size();
        this.totalFare = reservation.getTotalFare();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Long getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(Long totalFare) {
        this.totalFare = totalFare;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
