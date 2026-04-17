package GiorgiaFormicola.U5_W2_D5.controllers;

import GiorgiaFormicola.U5_W2_D5.entities.Employee;
import GiorgiaFormicola.U5_W2_D5.exceptions.PayloadValidationException;
import GiorgiaFormicola.U5_W2_D5.payloads.EmployeeDTO;
import GiorgiaFormicola.U5_W2_D5.services.EmployeesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@AllArgsConstructor
public class EmployeesController {
    private EmployeesService employeesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveNewEmployee(@RequestBody @Validated EmployeeDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new PayloadValidationException(errors);
        }
        return this.employeesService.save(body);
    }

}
