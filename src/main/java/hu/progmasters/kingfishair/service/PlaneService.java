package hu.progmasters.kingfishair.service;

import hu.progmasters.kingfishair.domain.Plane;
import hu.progmasters.kingfishair.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlaneService {

    private PlaneRepository planeRepository;

    @Autowired
    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }


    public List<Plane> getPlaneListForFlightForm() {
        return this.planeRepository.findAll();
    }

    public Plane getPlaneById(Long id) {
        return this.planeRepository.findById(id).orElse(null);
    }

}
