package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.Airport;
import hu.progmasters.kingfishair.domain.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationListItemForUser {

    private String departureAirportName;

    private String departureAirportCity;

    private String arrivalAirportName;

    private String arrivalAirportCity;

    private LocalDateTime stDeparture;

    private LocalDateTime stArrival;

    private List<SeatListItemForUser> seatListItemForUser;
    private long id;

    private boolean checkedinStatus;

    public ReservationListItemForUser(Reservation reservation) {
        this.departureAirportName = reservation.getFlight().getRoute().getDepartureAirport().getName();
        this.departureAirportCity = reservation.getFlight().getRoute().getDepartureAirport().getCity();
        this.arrivalAirportName = reservation.getFlight().getRoute().getArrivalAirport().getName();
        this.arrivalAirportCity = reservation.getFlight().getRoute().getArrivalAirport().getCity();
        this.stDeparture = reservation.getFlight().getStDeparture();
        this.stArrival = reservation.getFlight().getStArrival();
        this.seatListItemForUser = reservation.getSeatList().stream()
                .map(SeatListItemForUser::new).collect(Collectors.toList());
        this.id=reservation.getId();
        this.checkedinStatus=reservation.isCheckedIn();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCheckedinStatus() {
        return checkedinStatus;
    }

    public void setCheckedinStatus(boolean checkedinStatus) {
        this.checkedinStatus = checkedinStatus;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getDepartureAirportCity() {
        return departureAirportCity;
    }

    public void setDepartureAirportCity(String departureAirportCity) {
        this.departureAirportCity = departureAirportCity;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getArrivalAirportCity() {
        return arrivalAirportCity;
    }

    public void setArrivalAirportCity(String arrivalAirportCity) {
        this.arrivalAirportCity = arrivalAirportCity;
    }

    public LocalDateTime getStDeparture() {
        return stDeparture;
    }

    public void setStDeparture(LocalDateTime stDeparture) {
        this.stDeparture = stDeparture;
    }

    public LocalDateTime getStArrival() {
        return stArrival;
    }

    public void setStArrival(LocalDateTime stArrival) {
        this.stArrival = stArrival;
    }

    public List<SeatListItemForUser> getSeatListItemForUser() {
        return seatListItemForUser;
    }

    public void setSeatListItemForUser(List<SeatListItemForUser> seatListItemForUser) {
        this.seatListItemForUser = seatListItemForUser;
    }
}
