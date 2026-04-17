package GiorgiaFormicola.U5_W2_D5.controllers;

import GiorgiaFormicola.U5_W2_D5.entities.Trip;
import GiorgiaFormicola.U5_W2_D5.exceptions.PayloadValidationException;
import GiorgiaFormicola.U5_W2_D5.payloads.TripDTO;
import GiorgiaFormicola.U5_W2_D5.services.TripsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trips")
@AllArgsConstructor
public class TripsController {
    private TripsService tripsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trip saveNewTrip(@RequestBody @Validated TripDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new PayloadValidationException(errors);
        }
        return this.tripsService.save(body);
    }

    
}
