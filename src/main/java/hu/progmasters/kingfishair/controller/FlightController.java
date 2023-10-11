package hu.progmasters.kingfishair.controller;

import hu.progmasters.kingfishair.dto.adminFlightForm.CreateFlight;
import hu.progmasters.kingfishair.dto.adminFlightForm.PlaneListForFlightFormListItem;
import hu.progmasters.kingfishair.dto.adminFlightForm.RouteListForFlightFormListItem;
import hu.progmasters.kingfishair.dto.adminFlightList.AdminFlightListOrderFilter;
import hu.progmasters.kingfishair.dto.adminFlightList.FlightListItem;
import hu.progmasters.kingfishair.dto.adminRouteList.RouteListItem;
import hu.progmasters.kingfishair.dto.incoming.FlightDetails;
import hu.progmasters.kingfishair.dto.outgoing.FlightListItemDetails;
import hu.progmasters.kingfishair.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private FlightService flightService;

    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @GetMapping("/list-all-flights")
    public ResponseEntity<List<FlightDetails>> listAllFlights(){
        return new ResponseEntity<>(flightService.listAllFlights(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<FlightDetails>> deleteFlight(@PathVariable("id") Long id) {
        List<FlightDetails> remainingFlights = flightService.deleteFlight(id);
        logger.info("FLIGHT SUCCESSFULLY DELETED.");
        return new ResponseEntity<>(remainingFlights, HttpStatus.OK);
    }

    @GetMapping("/flight-details/{id}")
    public ResponseEntity<FlightListItemDetails> getFlightDetails(@PathVariable Long id) {
        FlightListItemDetails details = flightService.getFlightDetails(id);
        if (details != null) {
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-plane-list-for-flight-form")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<PlaneListForFlightFormListItem>> getPlaneListForFlightForm() {
        return new ResponseEntity<List<PlaneListForFlightFormListItem>>(
                this.flightService.getPlanetListForFlightForm(),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-route-list-for-flight-form")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<RouteListForFlightFormListItem>> getRouteListForFlightForm() {
        return new ResponseEntity<List<RouteListForFlightFormListItem>>(
                this.flightService.getRoutetListForFlightForm(),
                HttpStatus.OK
        );
    }

    @PostMapping("/create-flight")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity createFlight(@RequestBody CreateFlight createFlight) {
        this.flightService.createFlight(createFlight);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/get-flight-list-for-admin")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<FlightListItem>> getRouteForAdmin(@RequestBody AdminFlightListOrderFilter adminFlightListOrderFilter) {
        return new ResponseEntity<List<FlightListItem>>(
                this.flightService.getFlightForAdmin(adminFlightListOrderFilter)
                , HttpStatus.OK);

    }


}
