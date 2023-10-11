package hu.progmasters.kingfishair.dto.outgoing;

import hu.progmasters.kingfishair.domain.Flight;

import java.time.LocalDateTime;

public class FlightListItemDetails {

    private Long id;

    private LocalDateTime stDeparture;

    private String flightNumber;

    private String departureAirport;

    private String arrivalAirport;

    private Long flightTime;

    private String imageUrl;

    private String planeName;

    public FlightListItemDetails(Flight flight, String imageUrl) {
        this.imageUrl = imageUrl;
        this.planeName = flight.getPlane().getName();
        this.id = flight.getPlane().getId();
        this.flightNumber = flight.getFlightNumber();
        this.departureAirport = flight.getRoute().getDepartureAirport().getName();
        this.arrivalAirport = flight.getRoute().getArrivalAirport().getName();
        this.stDeparture = flight.getStDeparture();
        this.flightTime = flight.getRoute().getFlightTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStDeparture() {
        return stDeparture;
    }

    public void setStDeparture(LocalDateTime stDeparture) {
        this.stDeparture = stDeparture;
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

    public Long getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Long flightTime) {
        this.flightTime = flightTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

}
