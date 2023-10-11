package hu.progmasters.kingfishair.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @NotNull
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull
    private RegisteredUser customer;

    @OneToMany(mappedBy = "reservation")
    @NotNull
    private List<Seat> seatList;

    @Column
    @NotNull
    private Long totalFare;

    @Column
    private boolean isPaid;

    @Column
    private Date reservationHoldingDeadline;

    @Column
    private boolean checkedIn;


    public Reservation(Long id, Flight flight, RegisteredUser customer, List<Seat> seatList, Long totalFare, Date reservationHoldingDeadline) {
        this.id = id;
        this.flight = flight;
        this.customer = customer;
        this.seatList = seatList;
        this.totalFare = totalFare;
        this.isPaid = false;
        this.reservationHoldingDeadline=reservationHoldingDeadline;
        this.checkedIn=false;
    }

    public Reservation() {
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public RegisteredUser getCustomer() {
        return customer;
    }

    public void setCustomer(RegisteredUser customer) {
        this.customer = customer;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public Long getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(Long totalFare) {
        this.totalFare = totalFare;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Date getReservationHoldingDeadline() {
        return reservationHoldingDeadline;
    }

    public void setReservationHoldingDeadline(Date reservationHoldingDeadline) {
        this.reservationHoldingDeadline = reservationHoldingDeadline;
    }
}
