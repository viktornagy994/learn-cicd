package hu.progmasters.kingfishair.service;

import hu.progmasters.kingfishair.domain.Seat;
import hu.progmasters.kingfishair.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SeatService {

    private SeatRepository seatRepository;
@Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }



    public Seat findSeatById(Long id){
    return this.seatRepository.findById(id).orElseThrow();
    }


    public List<Seat> findAllByReservationId(Long reservationId) {
      return this.seatRepository.findAllByReservationId(reservationId);
    }


}
