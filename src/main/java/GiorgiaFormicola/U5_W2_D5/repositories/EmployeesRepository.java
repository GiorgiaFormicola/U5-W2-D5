package GiorgiaFormicola.U5_W2_D5.repositories;

import GiorgiaFormicola.U5_W2_D5.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, UUID> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
