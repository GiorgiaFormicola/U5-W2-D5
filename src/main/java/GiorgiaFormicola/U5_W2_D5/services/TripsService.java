package GiorgiaFormicola.U5_W2_D5.services;

import GiorgiaFormicola.U5_W2_D5.entities.Trip;
import GiorgiaFormicola.U5_W2_D5.exceptions.BadRequestException;
import GiorgiaFormicola.U5_W2_D5.payloads.TripDTO;
import GiorgiaFormicola.U5_W2_D5.repositories.TripsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TripsService {
    private TripsRepository tripsRepository;

    public Trip save(TripDTO body) {
        if (tripsRepository.existsByDestinationAndDate(body.destination(), body.date()))
            throw new BadRequestException("Trip for " + body.destination() + " on date " + body.date() + " already planned!");
        Trip newTrip = new Trip(body.destination(), body.date());
        Trip savedTrip = this.tripsRepository.save(newTrip);
        log.info("Trip with id " + savedTrip.getId() + " successfully saved!");
        return savedTrip;
    }

    public Page<Trip> findAll(int page, int size) {
        if (page < 0) page = 0;
        if (size < 0 || size > 100) size = 5;
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").reverse());
        return this.tripsRepository.findAll(pageable);
    }
}
