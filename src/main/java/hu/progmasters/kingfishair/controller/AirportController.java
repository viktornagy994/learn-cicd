package hu.progmasters.kingfishair.controller;

import hu.progmasters.kingfishair.service.AirportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airports")
public class AirportController {


    private AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }
}
