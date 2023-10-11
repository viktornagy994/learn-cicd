package hu.progmasters.kingfishair.dto.adminRouteForm;

public class CreateRoute {

    private Long departureAirportId;

    private Long arrivalAirportId;

    private Long distance;

    private Long flightTime;

    private Long ticketFare;


    public CreateRoute() {
    }


    public Long getDepartureAirportId() {
        return this.departureAirportId;
    }

    public void setDepartureAirportId(Long departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Long getArrivalAirportId() {
        return this.arrivalAirportId;
    }

    public void setArrivalAirportId(Long arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public Long getDistance() {
        return this.distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getFlightTime() {
        return this.flightTime;
    }

    public void setFlightTime(Long flightTime) {
        this.flightTime = flightTime;
    }

    public Long getTicketFare() {
        return this.ticketFare;
    }

    public void setTicketFare(Long ticketFare) {
        this.ticketFare = ticketFare;
    }

}
