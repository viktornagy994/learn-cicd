package hu.progmasters.kingfishair.dto.incoming;

import hu.progmasters.kingfishair.domain.Flight;

import java.time.LocalDateTime;

public class FlightDetails {

    private Long id;

    private String flightNumber;

    private String departureAirport;

    private String arrivalAirport;

    private LocalDateTime stDeparture;

    private LocalDateTime stArrival;

    public FlightDetails() {
    }

    public FlightDetails(Flight flight) {
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNumber();
        this.departureAirport = flight.getRoute().getDepartureAirport().getIata();
        this.arrivalAirport = flight.getRoute().getArrivalAirport().getIata();
        this.stDeparture = flight.getStDeparture();
        this.stArrival = flight.getStArrival();
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

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
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
}
