package hu.progmasters.kingfishair.controller;

import hu.progmasters.kingfishair.dto.adminRouteForm.AirportListForRouteFormListItem;
import hu.progmasters.kingfishair.dto.adminRouteForm.CreateRoute;
import hu.progmasters.kingfishair.dto.adminRouteList.AdminRouteListOrderFilter;
import hu.progmasters.kingfishair.dto.adminRouteList.RouteListItem;
import hu.progmasters.kingfishair.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/get-airport-list-for-route-form")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<AirportListForRouteFormListItem>> getAirportListForRouteForm() {
        return new ResponseEntity<List<AirportListForRouteFormListItem>>(this.routeService.getAirportListForRouteForm(), HttpStatus.OK);

    }

    @PostMapping("/create-route")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity createRoute(@RequestBody CreateRoute createRoute) {
        this.routeService.createRoute(createRoute);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/get-route-list-for-admin")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<RouteListItem>> getRouteForAdmin(@RequestBody AdminRouteListOrderFilter adminRouteListOrderFilter) {
        return new ResponseEntity<List<RouteListItem>>(
                this.routeService.getRouteForAdmin(adminRouteListOrderFilter)
                , HttpStatus.OK);

    }





}
