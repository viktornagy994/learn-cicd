package hu.progmasters.kingfishair.dto.adminFlightForm;

import hu.progmasters.kingfishair.domain.Route;

public class RouteListForFlightFormListItem {

    private Long id;

    private String departureDisplayName;

    private String arrivalDisplayName;

    private Long distance;

    private Long flightTime;

    private Long ticketFare;


    public RouteListForFlightFormListItem() {
    }

    public RouteListForFlightFormListItem(Route route) {
        this.id                   = route.getId();
        this.departureDisplayName = route.getDepartureAirport().getName() + ", " + route.getDepartureAirport().getCity() + " (" + route.getDepartureAirport().getIata() + ")";
        this.arrivalDisplayName   = route.getArrivalAirport().getName()   + ", " + route.getArrivalAirport().getCity()   + " (" + route.getArrivalAirport().getIata() + ")";
        this.distance             = route.getDistance();
        this.flightTime           = route.getFlightTime();
        this.ticketFare           = route.getTicketFare();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartureDisplayName() {
        return departureDisplayName;
    }

    public void setDepartureDisplayName(String departureDisplayName) {
        this.departureDisplayName = departureDisplayName;
    }

    public String getArrivalDisplayName() {
        return arrivalDisplayName;
    }

    public void setArrivalDisplayName(String arrivalDisplayName) {
        this.arrivalDisplayName = arrivalDisplayName;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Long flightTime) {
        this.flightTime = flightTime;
    }

    public Long getTicketFare() {
        return ticketFare;
    }

    public void setTicketFare(Long ticketFare) {
        this.ticketFare = ticketFare;
    }
}
