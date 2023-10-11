package hu.progmasters.kingfishair.repository;

import hu.progmasters.kingfishair.domain.Flight;
import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("select r from Reservation r where r.customer = :customer and r.flight.stDeparture>:std and r.isPaid =true order by r.flight.stDeparture ASC")
    List<Reservation> findAllByCustomerForFuture(@Param("customer")RegisteredUser registeredUser, @Param("std")LocalDateTime localDateTime);

    @Query("select r from Reservation r where r.customer = :customer and r.flight.stDeparture<:std order by r.flight.stDeparture DESC")
    List<Reservation> findAllByCustomerForPast(@Param("customer")RegisteredUser registeredUser, @Param("std")LocalDateTime localDateTime);
    @Query("select r from Reservation r where r.customer = :customer and r.isPaid =false and r.reservationHoldingDeadline> :date and r.flight.stDeparture>:std order by r.flight.stDeparture DESC")
    List<Reservation> findAllByCustomerForPending(@Param("customer")RegisteredUser registeredUser, @Param("date")Date date,@Param("std")LocalDateTime localDateTime);


    @Query("select r from Reservation r where r.flight = :flight and r.isPaid =true or r.reservationHoldingDeadline> :date")
    List<Reservation> findAllByFlight(@Param("flight")Flight flight, @Param("date") Date date);

    @Query("SELECT r"
            + " FROM Reservation AS r"
            + " WHERE r.flight.id = :flightid"
            + " ORDER BY r.id"
    )
    List<Reservation> findAllByFlightId(@Param("flightid") Long flightId);

}
