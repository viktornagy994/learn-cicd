package hu.progmasters.kingfishair.repository;

import hu.progmasters.kingfishair.domain.Airport;
import hu.progmasters.kingfishair.domain.Route;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {



    @Query("select r from Route r where r.departureAirport = :dep AND r.arrivalAirport = :arr")
    Optional <Route> findByAirports(@Param("dep") Airport departureAirport, @Param("arr") Airport arrivalAirport);


    @Query("SELECT r"
            + " FROM Route AS r"
            + " LEFT JOIN Airport AS a1 ON r.departureAirport.id = a1.id"
            + " LEFT JOIN Airport AS a2 ON r.arrivalAirport.id = a2.id"
            + " WHERE (    (UPPER(a1.iata) LIKE %:dep%)"
                    + " OR (UPPER(a1.name) LIKE %:dep%)"
                    + " OR (UPPER(a1.city) LIKE %:dep%))"
              + " AND (    (UPPER(a2.iata) LIKE %:arr%)"
                    + " OR (UPPER(a2.name) LIKE %:arr%)"
                    + " OR (UPPER(a2.city) LIKE %:arr%))"
            + " ORDER BY a1.name"
//            + " ORDER BY :#{#sort.toString()}"
    )
    List<Route> findAllByParamsOrderDepName(@Param("dep") String depAirportName,
                                            @Param("arr") String arrAirportName,
                                            Sort sort);

    @Query("SELECT r"
            + " FROM Route AS r"
            + " LEFT JOIN Airport AS a1 ON r.departureAirport.id = a1.id"
            + " LEFT JOIN Airport AS a2 ON r.arrivalAirport.id = a2.id"
            + " WHERE (    (UPPER(a1.iata) LIKE %:dep%)"
                    + " OR (UPPER(a1.name) LIKE %:dep%)"
                    + " OR (UPPER(a1.city) LIKE %:dep%))"
              + " AND (    (UPPER(a2.iata) LIKE %:arr%)"
                    + " OR (UPPER(a2.name) LIKE %:arr%)"
                    + " OR (UPPER(a2.city) LIKE %:arr%))"
            + " ORDER BY a2.name"
    )
    List<Route> findAllByParamsOrderArrName(@Param("dep") String depAirportName, @Param("arr") String arrAirportName, Sort sort);



}
