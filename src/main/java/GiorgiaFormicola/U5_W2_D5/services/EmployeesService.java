package GiorgiaFormicola.U5_W2_D5.services;

import GiorgiaFormicola.U5_W2_D5.entities.Employee;
import GiorgiaFormicola.U5_W2_D5.exceptions.BadRequestException;
import GiorgiaFormicola.U5_W2_D5.payloads.EmployeeDTO;
import GiorgiaFormicola.U5_W2_D5.repositories.EmployeesRepository;
import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeesService {
    private EmployeesRepository employeesRepository;
    private Cloudinary cloudinary;

    public Employee save(EmployeeDTO body) {
        if (employeesRepository.existsByEmail(body.email()))
            throw new BadRequestException("Email " + body.email() + " already in use!");
        if (employeesRepository.existsByUsername(body.username()))
            throw new BadRequestException("Username " + body.username() + " already in use!");
        Employee newEmployee = new Employee(body.username(), body.name(), body.surname(), body.email());
        Employee savedEmployee = this.employeesRepository.save(newEmployee);
        log.info("Employee with id " + savedEmployee.getId() + " successfully saved!");
        return savedEmployee;
    }
}
