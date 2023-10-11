package hu.progmasters.kingfishair.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    @NotNull
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "route_id")
    @NotNull
    private Route route;
    @Column
    @NotNull
    private LocalDateTime stDeparture;

    @Column
    @NotNull
    private LocalDateTime stArrival;

    @ManyToOne
    @JoinColumn(name = "plane_id")
    @NotNull
    private Plane plane;

    @OneToMany(mappedBy = "flight")
    private List<Reservation> reservationList;

    public Flight() {
    }

    public Flight(Long id, String flightNumber, Route route, LocalDateTime stDeparture, LocalDateTime stArrival, Plane plane, List<Reservation> reservationList) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.route = route;
        this.stDeparture = stDeparture;
        this.stArrival = stArrival;
        this.plane = plane;
        this.reservationList = reservationList;
    }

    public Flight(String flightNumber, Route route, LocalDateTime stDeparture, LocalDateTime stArrival, Plane plane) {
        this.flightNumber = flightNumber;
        this.route = route;
        this.stDeparture = stDeparture;
        this.stArrival = stArrival;
        this.plane = plane;
        this.reservationList = new ArrayList<>();
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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
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

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
