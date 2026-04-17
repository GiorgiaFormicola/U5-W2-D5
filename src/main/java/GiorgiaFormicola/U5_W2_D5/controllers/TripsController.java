package GiorgiaFormicola.U5_W2_D5.controllers;

import GiorgiaFormicola.U5_W2_D5.entities.Trip;
import GiorgiaFormicola.U5_W2_D5.exceptions.PayloadValidationException;
import GiorgiaFormicola.U5_W2_D5.payloads.TripDTO;
import GiorgiaFormicola.U5_W2_D5.payloads.TripDateDTO;
import GiorgiaFormicola.U5_W2_D5.services.TripsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
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

    @GetMapping
    public Page<Trip> getTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return this.tripsService.findAll(page, size);
    }

    @GetMapping("/{tripId}")
    public Trip getTripById(@PathVariable UUID tripId) {
        return this.tripsService.findById(tripId);
    }

    @PutMapping("/{tripId}")
    public Trip getTripByIdAndUpdate(@PathVariable UUID tripId, @RequestBody @Validated TripDateDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new PayloadValidationException(errorsList);
        }
        return this.tripsService.findByIdAndUpdate(tripId, body);
    }


}
