package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@Tag(name = "Gestion Réservations")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {

    private final IReservationService reservationService;   // injection par constructeur (via Lombok)

    @Operation(description = "Récupérer les réservations")
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> retrieveAllReservations() {
        return reservationService.retrieveAllReservations();
    }

    @Operation(description = "Récupérer une réservation par son id")
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") Long id) {
        return reservationService.retrieveReservation(id);
    }

    @Operation(description = "Ajouter une réservation")
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @Operation(description = "Ajouter une liste : les réservations")
    @PostMapping("/add-reservations")
    public List<Reservation> addReservations(@RequestBody List<Reservation> reservations) {
        return reservationService.addReservations(reservations);
    }

    @Operation(description = "Modifier une réservation")
    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

    @Operation(description = "Supprimer une réservation")
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") Long id) {
        reservationService.removeReservation(id);
    }
}
