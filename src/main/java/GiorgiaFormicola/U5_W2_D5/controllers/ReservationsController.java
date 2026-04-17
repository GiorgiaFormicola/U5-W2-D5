package GiorgiaFormicola.U5_W2_D5.controllers;

import GiorgiaFormicola.U5_W2_D5.entities.Reservation;
import GiorgiaFormicola.U5_W2_D5.exceptions.PayloadValidationException;
import GiorgiaFormicola.U5_W2_D5.payloads.ReservationDTO;
import GiorgiaFormicola.U5_W2_D5.services.ReservationsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("reservations")
@AllArgsConstructor
public class ReservationsController {
    private ReservationsService reservationsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation saveNewReservation(@RequestBody @Validated ReservationDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new PayloadValidationException(errors);
        }
        return this.reservationsService.save(body);
    }

    @GetMapping
    public Page<Reservation> getReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "trip.date") String sortBy) {
        return this.reservationsService.findAll(page, size, sortBy);
    }

    @GetMapping("/{reservationId}")
    public Reservation getReservationById(@PathVariable UUID reservationId) {
        return this.reservationsService.findById(reservationId);
    }


}
