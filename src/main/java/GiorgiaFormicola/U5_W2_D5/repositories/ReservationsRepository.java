package GiorgiaFormicola.U5_W2_D5.repositories;

import GiorgiaFormicola.U5_W2_D5.entities.Employee;
import GiorgiaFormicola.U5_W2_D5.entities.Reservation;
import GiorgiaFormicola.U5_W2_D5.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, UUID> {
    boolean existsByTrip(Trip trip);

    boolean existsByEmployeeAndTrip_Date(Employee employee, LocalDate tripDate);
}
