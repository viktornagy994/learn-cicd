package hu.progmasters.kingfishair.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    @NotNull
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    @NotNull
    private Airport arrivalAirport;

    @Column
    @NotNull
    private Long distance;

    @Column
    @NotNull
    private Long flightTime;

    @Column
    @NotNull
    private Long ticketFare;

    @OneToMany(mappedBy = "route")
    private List<Flight> flightList;


    public Route() {
    }

    public Route(Long id, Airport departureAirport, Airport arrivalAirport, Long distance, Long flightTime, Long ticketFare, List<Flight> flightList) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.distance = distance;
        this.flightTime = flightTime;
        this.ticketFare = ticketFare;
        this.flightList = flightList;
    }

    public Route(Airport departureAirport, Airport arrivalAirport, Long distance, Long flightTime, Long ticketFare) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.distance = distance;
        this.flightTime = flightTime;
        this.ticketFare = ticketFare;
        this.flightList = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
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

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
}
