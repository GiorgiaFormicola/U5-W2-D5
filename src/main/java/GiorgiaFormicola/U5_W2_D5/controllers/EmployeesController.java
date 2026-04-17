package GiorgiaFormicola.U5_W2_D5.controllers;

import GiorgiaFormicola.U5_W2_D5.services.EmployeesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employees")
@AllArgsConstructor
public class EmployeesController {
    private EmployeesService employeesService;
    
}
