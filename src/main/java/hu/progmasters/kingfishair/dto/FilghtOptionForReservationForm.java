package hu.progmasters.kingfishair.dto;

import hu.progmasters.kingfishair.domain.Flight;
import hu.progmasters.kingfishair.domain.Plane;
import hu.progmasters.kingfishair.domain.Reservation;
import hu.progmasters.kingfishair.domain.Route;
import java.time.LocalDateTime;
import java.util.List;

public class FilghtOptionForReservationForm {
    private Long id;

    private String flightNumber;

    private LocalDateTime stDeparture;

    private LocalDateTime stArrival;

    private String planeName;
    private Long freeSeats;

    public FilghtOptionForReservationForm() {
    }

    public FilghtOptionForReservationForm(Flight flight) {
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNumber();
        this.stDeparture = flight.getStDeparture();
        this.stArrival = flight.getStArrival();
        this.planeName = flight.getPlane().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
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

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public Long getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Long freeSeats) {
        this.freeSeats = freeSeats;
    }
}
