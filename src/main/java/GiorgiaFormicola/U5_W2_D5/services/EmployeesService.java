package GiorgiaFormicola.U5_W2_D5.services;

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
}
