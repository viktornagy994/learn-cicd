package hu.progmasters.kingfishair.repository;

import hu.progmasters.kingfishair.domain.Reservation;
import hu.progmasters.kingfishair.domain.Route;
import hu.progmasters.kingfishair.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {


    @Query("SELECT s"
            + " FROM Seat AS s"
            + " WHERE s.reservation.id = :resId"
            + " ORDER BY s.id"
    )
    List<Seat> findAllByReservationId(@Param("resId") Long reservationId);


}
