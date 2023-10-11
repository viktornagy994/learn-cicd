package hu.progmasters.kingfishair.service;

import hu.progmasters.kingfishair.domain.Airport;
import hu.progmasters.kingfishair.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AirportService {

    private AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }


    public List<Airport> getAirportListForRouteForm() {
        return this.airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return this.airportRepository.findById(id).orElse(null);
    }





    public List<Airport> getAirportListForReservationForm() {
        return this.airportRepository.findAll();
    }

}
