package hu.progmasters.kingfishair.dto.adminFlightForm;

import java.time.LocalDateTime;

public class CreateFlight {

    private String flightNumber;

    private Long routeId;

    private LocalDateTime stDeparture;

    private LocalDateTime stArrival;

    private Long planeId;


    public CreateFlight() {
    }


    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Long getRouteId() {
        return this.routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public LocalDateTime getStDeparture() {
        return this.stDeparture;
    }

    public void setStDeparture(LocalDateTime stDeparture) {
        this.stDeparture = stDeparture;
    }

    public LocalDateTime getStArrival() {
        return this.stArrival;
    }

    public void setStArrival(LocalDateTime stArrival) {
        this.stArrival = stArrival;
    }

    public Long getPlaneId() {
        return this.planeId;
    }

    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }


}
