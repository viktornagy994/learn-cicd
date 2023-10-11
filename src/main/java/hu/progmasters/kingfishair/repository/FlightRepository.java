package hu.progmasters.kingfishair.repository;

import hu.progmasters.kingfishair.domain.Flight;
import hu.progmasters.kingfishair.domain.Route;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("select f from Flight f where f.stDeparture>:std AND f.route=:route order by f.stDeparture")
    List<Flight> findAllByRoute(@Param("route") Route route, @Param("std")LocalDateTime localDateTime);


    @Query("SELECT f"
            + " FROM Flight AS f"
            + " LEFT JOIN Route AS r ON f.route.id = r.id"
            + " LEFT JOIN Airport AS a1 ON r.departureAirport.id = a1.id"
            + " LEFT JOIN Airport AS a2 ON r.arrivalAirport.id = a2.id"

            + " WHERE (    (UPPER(f.flightNumber) LIKE %:fnr%))"

            +" AND (    (UPPER(a1.iata) LIKE %:dep%)"
            + " OR (UPPER(a1.name) LIKE %:dep%)"
            + " OR (UPPER(a1.city) LIKE %:dep%))"

            + " AND (    (UPPER(a2.iata) LIKE %:arr%)"
            + " OR (UPPER(a2.name) LIKE %:arr%)"
            + " OR (UPPER(a2.city) LIKE %:arr%))"

            + " ORDER BY f.flightNumber"
    )
    List<Flight> findAllOrderFlightNr(@Param("fnr") String filterFlightNr, @Param("dep") String depAirportName, @Param("arr") String arrAirportName);

    @Query("SELECT f"
            + " FROM Flight AS f"
            + " LEFT JOIN Route AS r ON f.route.id = r.id"
            + " LEFT JOIN Airport AS a1 ON r.departureAirport.id = a1.id"
            + " LEFT JOIN Airport AS a2 ON r.arrivalAirport.id = a2.id"
            + " WHERE (    (UPPER(f.flightNumber) LIKE %:fnr%))"
              + " AND (    (UPPER(a1.iata) LIKE %:dep%)"
                    + " OR (UPPER(a1.name) LIKE %:dep%)"
                    + " OR (UPPER(a1.city) LIKE %:dep%))"
              + " AND (    (UPPER(a2.iata) LIKE %:arr%)"
                    + " OR (UPPER(a2.name) LIKE %:arr%)"
                    + " OR (UPPER(a2.city) LIKE %:arr%))"
            + " ORDER BY f.flightNumber"
    )
    List<Flight> findAllByParamsOrderFlightNr(@Param("fnr") String filterFlightNr, @Param("dep") String depAirportName, @Param("arr") String arrAirportName);

    @Query("SELECT f"
            + " FROM Flight AS f"
            + " LEFT JOIN Route AS r ON f.route.id = r.id"
            + " LEFT JOIN Airport AS a1 ON r.departureAirport.id = a1.id"
            + " LEFT JOIN Airport AS a2 ON r.arrivalAirport.id = a2.id"
            + " WHERE (    (UPPER(f.flightNumber) LIKE %:fnr%))"
              + " AND (    (UPPER(a1.iata) LIKE %:dep%)"
                    + " OR (UPPER(a1.name) LIKE %:dep%)"
                    + " OR (UPPER(a1.city) LIKE %:dep%))"
              + " AND (    (UPPER(a2.iata) LIKE %:arr%)"
                    + " OR (UPPER(a2.name) LIKE %:arr%)"
                    + " OR (UPPER(a2.city) LIKE %:arr%))"
            + " ORDER BY a1.name"
    )
    List<Flight> findAllByParamsOrderDepName(@Param("fnr") String filterFlightNr, @Param("dep") String depAirportName, @Param("arr") String arrAirportName);


    @Query("SELECT f"
            + " FROM Flight AS f"
            + " LEFT JOIN Route AS r ON f.route.id = r.id"
            + " LEFT JOIN Airport AS a1 ON r.departureAirport.id = a1.id"
            + " LEFT JOIN Airport AS a2 ON r.arrivalAirport.id = a2.id"
            + " WHERE (    (UPPER(f.flightNumber) LIKE %:fnr%))"
              + " AND (    (UPPER(a1.iata) LIKE %:dep%)"
                    + " OR (UPPER(a1.name) LIKE %:dep%)"
                    + " OR (UPPER(a1.city) LIKE %:dep%))"
              + " AND (    (UPPER(a2.iata) LIKE %:arr%)"
                    + " OR (UPPER(a2.name) LIKE %:arr%)"
                    + " OR (UPPER(a2.city) LIKE %:arr%))"
            + " ORDER BY a2.name"
    )
    List<Flight> findAllByParamsOrderArrName(@Param("fnr") String filterFlightNr, @Param("dep") String depAirportName, @Param("arr") String arrAirportName);

}
