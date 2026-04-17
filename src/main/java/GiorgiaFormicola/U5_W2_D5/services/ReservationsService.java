package GiorgiaFormicola.U5_W2_D5.services;

import GiorgiaFormicola.U5_W2_D5.entities.Employee;
import GiorgiaFormicola.U5_W2_D5.entities.Reservation;
import GiorgiaFormicola.U5_W2_D5.entities.Trip;
import GiorgiaFormicola.U5_W2_D5.enums.TripStatus;
import GiorgiaFormicola.U5_W2_D5.exceptions.BadRequestException;
import GiorgiaFormicola.U5_W2_D5.exceptions.NotFoundException;
import GiorgiaFormicola.U5_W2_D5.payloads.ReservationDTO;
import GiorgiaFormicola.U5_W2_D5.repositories.ReservationsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationsService {
    private ReservationsRepository reservationsRepository;
    private EmployeesService employeesService;
    private TripsService tripsService;

    public Reservation save(ReservationDTO body) {
        Employee employeeFound = employeesService.findById(body.employeeId());
        Trip tripFound = tripsService.findById(body.tripId());
        if (tripFound.getStatus().equals(TripStatus.COMPLETED))
            throw new BadRequestException("Trip has already taken place on " + tripFound.getDate());
        if (reservationsRepository.existsByTrip(tripFound))
            throw new BadRequestException("Trip for " + tripFound.getDestination() + " on " + tripFound.getDate() + " already reserved by another employee");
        if (reservationsRepository.existsByEmployeeAndTrip_Date(employeeFound, tripFound.getDate()))
            throw new BadRequestException("Employee has already a trip reserved on " + tripFound.getDate());
        Reservation newReservation = new Reservation(body.notes(), employeeFound, tripFound);
        Reservation savedReservation = this.reservationsRepository.save(newReservation);
        log.info("Reservation with id " + savedReservation.getId() + " successfully saved!");
        return savedReservation;
    }

    public Page<Reservation> findAll(int page, int size, String sortBy) {
        if (page < 0) page = 0;
        if (size < 0 || size > 100) size = 5;
        Pageable pageable;
        if (sortBy.equals("trip.date") || sortBy.equals("requestDate"))
            pageable = PageRequest.of(page, size, Sort.by(sortBy).reverse());
        else pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.reservationsRepository.findAll(pageable);
    }

    public Reservation findById(UUID reservationId) {
        return this.reservationsRepository.findById(reservationId).orElseThrow(() -> new NotFoundException("reservation", reservationId));
    }


}
