package hu.progmasters.kingfishair.service;

import hu.progmasters.kingfishair.domain.Airport;
import hu.progmasters.kingfishair.domain.Route;
import hu.progmasters.kingfishair.dto.adminRouteForm.AirportListForRouteFormListItem;
import hu.progmasters.kingfishair.dto.adminRouteForm.CreateRoute;
import hu.progmasters.kingfishair.dto.adminRouteList.AdminRouteListOrderFilter;
import hu.progmasters.kingfishair.dto.adminRouteList.RouteListItem;
import hu.progmasters.kingfishair.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class RouteService {


    private RouteRepository routeRepository;

    private AirportService airportService;


    @Autowired
    public RouteService(RouteRepository routeRepository, AirportService airportService) {
        this.routeRepository = routeRepository;
        this.airportService = airportService;
    }


    public List<AirportListForRouteFormListItem> getAirportListForRouteForm() {
        List<Airport> airportList = this.airportService.getAirportListForRouteForm();
        List<AirportListForRouteFormListItem> response = new ArrayList<>();
        for (Airport airport: airportList) {
            response.add(new AirportListForRouteFormListItem(airport));
        }
        return response;
    }

    public void createRoute(CreateRoute createRoute) {
        Route route = new Route(
                this.airportService.getAirportById(createRoute.getDepartureAirportId()),
                this.airportService.getAirportById(createRoute.getArrivalAirportId()),
                createRoute.getDistance(),
                createRoute.getFlightTime(),
                createRoute.getTicketFare()
        );
        this.routeRepository.save(route);
    }

    public List<Route> getRouteListForFlightForm() {
        return this.routeRepository.findAll();
    }

    public Route getRouteById(Long id) {
        return this.routeRepository.findById(id).orElse(null);
    }

    public List<RouteListItem> getRouteForAdmin(AdminRouteListOrderFilter aRLOF) {
//        List<Route> routeList = this.routeRepository.findAll();

        Sort sort = Sort.by(Sort.Direction.ASC, "a1.name");

        List<Route> routeList = new ArrayList<>();
        if (aRLOF.getOrder() == 1) {
            routeList = this.routeRepository.findAllByParamsOrderDepName(aRLOF.getFilterDepAirportName(), aRLOF.getFilterArrAirportName(), sort);
        } else {
            routeList = this.routeRepository.findAllByParamsOrderArrName(aRLOF.getFilterDepAirportName(), aRLOF.getFilterArrAirportName(), sort);
        }

        List<RouteListItem> response = new ArrayList<>();
        int sum = 0;
        for (Route route : routeList) {
            if (     (sum >= (aRLOF.getPage() * aRLOF.getItemPerPage()))
                  && (sum < ((aRLOF.getPage()+1) * aRLOF.getItemPerPage()))
            ) {
                response.add(new RouteListItem(
                        route.getId(),
                        route.getDepartureAirport().getIata(),
                        route.getDepartureAirport().getName(),
                        route.getDepartureAirport().getCity(),
                        route.getDepartureAirport().getCountry(),
                        route.getArrivalAirport().getIata(),
                        route.getArrivalAirport().getName(),
                        route.getArrivalAirport().getCity(),
                        route.getArrivalAirport().getCountry(),
                        route.getDistance(),
                        route.getFlightTime(),
                        route.getTicketFare()
                ));
            }
            sum++;
        }

        return response;
    }

    public Route getRouteByAirports(Airport departureAirport, Airport arrivalAirport) {
        Route route = routeRepository.findByAirports(departureAirport, arrivalAirport).orElseThrow(EntityNotFoundException::new);
        return route;
    }
    public Route findById(long id){
        return this.routeRepository.findById(id).orElseThrow(null);
    }
}
