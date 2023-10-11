package hu.progmasters.kingfishair.service;

import hu.progmasters.kingfishair.domain.Flight;
import hu.progmasters.kingfishair.domain.Image;
import hu.progmasters.kingfishair.domain.Plane;
import hu.progmasters.kingfishair.domain.Route;
import hu.progmasters.kingfishair.dto.adminFlightForm.CreateFlight;
import hu.progmasters.kingfishair.dto.adminFlightForm.PlaneListForFlightFormListItem;
import hu.progmasters.kingfishair.dto.adminFlightForm.RouteListForFlightFormListItem;
import hu.progmasters.kingfishair.dto.adminFlightList.AdminFlightListOrderFilter;
import hu.progmasters.kingfishair.dto.adminFlightList.FlightListItem;
import hu.progmasters.kingfishair.dto.adminRouteList.RouteListItem;
import hu.progmasters.kingfishair.dto.incoming.FlightDetails;
import hu.progmasters.kingfishair.dto.outgoing.FlightListItemDetails;
import hu.progmasters.kingfishair.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
@Transactional
public class FlightService {

    private FlightRepository flightRepository;
    private ImageService imageService;
    private PlaneService planeService;
    private RouteService routeService;


    @Autowired
    public FlightService(FlightRepository flightRepository, ImageService imageService, PlaneService planeService, RouteService routeService) {
        this.flightRepository = flightRepository;
        this.imageService = imageService;
        this.planeService = planeService;
        this.routeService = routeService;
    }

    public List<FlightDetails> listAllFlights() {
        return flightRepository.findAll().stream()
                .map(FlightDetails::new).collect(Collectors.toList());
    }

    public List<FlightDetails> deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Flight not found."));
        flightRepository.delete(flight);
        return flightRepository.findAll().stream().map(FlightDetails::new).collect(Collectors.toList());
    }

    public FlightListItemDetails getFlightDetails(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Flight not found"));

        Image image = imageService.getPlaneImageUrl(flight.getPlane().getId());
        String imageUrl = image.getImageUrl();

        return new FlightListItemDetails(flight, imageUrl);
    }


    public List<PlaneListForFlightFormListItem> getPlanetListForFlightForm() {
        List<Plane> planeList = this.planeService.getPlaneListForFlightForm();
        List<PlaneListForFlightFormListItem> response = new ArrayList<>();
        for (Plane plane : planeList) {
            response.add(new PlaneListForFlightFormListItem(plane));
        }
        return response;
    }


    public List<RouteListForFlightFormListItem> getRoutetListForFlightForm() {
        List<Route> routeList = this.routeService.getRouteListForFlightForm();
        List<RouteListForFlightFormListItem> response = new ArrayList<>();
        for (Route route : routeList) {
            response.add(new RouteListForFlightFormListItem(route));
        }
        return response;
    }

    public void createFlight(CreateFlight createFlight) {
        Flight flight = new Flight(
                createFlight.getFlightNumber(),
                this.routeService.getRouteById(createFlight.getRouteId()),
                createFlight.getStDeparture(),
                createFlight.getStArrival(),
                this.planeService.getPlaneById(createFlight.getPlaneId())
        );
        this.flightRepository.save(flight);
    }



    public List<Flight> getFlightsByRoute(Route route, LocalDateTime datePicker) {
        return this.flightRepository.findAllByRoute(route, datePicker);
    }

    public Flight findById(Long id) {
        return this.flightRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<FlightListItem> getFlightForAdmin(AdminFlightListOrderFilter aFLOF) {
        List<Flight> flightList = new ArrayList<>();

        if (aFLOF.getOrder() == 1) {
            flightList = this.flightRepository.findAllByParamsOrderFlightNr(
                    aFLOF.getFilterFlightNr().toUpperCase(),
                    aFLOF.getFilterDepAirportName().toUpperCase(),
                    aFLOF.getFilterArrAirportName().toUpperCase()
            );
        }
        if (aFLOF.getOrder() == 2) {
            flightList = this.flightRepository.findAllByParamsOrderDepName(
                    aFLOF.getFilterFlightNr().toUpperCase(),
                    aFLOF.getFilterDepAirportName().toUpperCase(),
                    aFLOF.getFilterArrAirportName().toUpperCase()
            );
        }
        if (aFLOF.getOrder() == 3) {
            flightList = this.flightRepository.findAllByParamsOrderArrName(
                    aFLOF.getFilterFlightNr().toUpperCase(),
                    aFLOF.getFilterDepAirportName().toUpperCase(),
                    aFLOF.getFilterArrAirportName().toUpperCase()
            );
        }

        List<FlightListItem> response = new ArrayList<>();
        int sum = 0;
        for (Flight flight : flightList) {
            if (     (sum >= (aFLOF.getPage() * aFLOF.getItemPerPage()))
                    && (sum < ((aFLOF.getPage()+1) * aFLOF.getItemPerPage()))
            ) {
                response.add(new FlightListItem(
                        flight.getId(),
                        flight.getFlightNumber(),
                        flight.getRoute().getDepartureAirport().getIata(),
                        flight.getRoute().getDepartureAirport().getName(),
                        flight.getRoute().getDepartureAirport().getCity(),
                        flight.getRoute().getDepartureAirport().getCountry(),
                        flight.getRoute().getArrivalAirport().getIata(),
                        flight.getRoute().getArrivalAirport().getName(),
                        flight.getRoute().getArrivalAirport().getCity(),
                        flight.getRoute().getArrivalAirport().getCountry(),
                        flight.getStDeparture(),
                        flight.getStArrival(),
                        flight.getPlane().getRegistrationNumber()
                ));
            }
            sum++;
        }

        return response;
    }


}

