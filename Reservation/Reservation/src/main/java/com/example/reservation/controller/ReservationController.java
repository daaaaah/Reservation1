package com.example.reservation.controller;

import com.example.reservation.dto.ReservationDTO;
import com.example.reservation.dto.StoreDTO;
import com.example.reservation.dto.UserDTO;
import com.example.reservation.model.Reservation;
import com.example.reservation.model.ReservationStatus;
import com.example.reservation.model.Store;
import com.example.reservation.model.User;
import com.example.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ReservationDTO makeReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation savedReservation = reservationService.makeReservation(reservationDTO);
        return convertToDTO(savedReservation);
    }

    private UserDTO convertUserToDTO(User user) {
        // Assuming UserDTO has a constructor that accepts all User fields.
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getName());
    }

    private StoreDTO convertStoreToDTO(Store store) {
        // Similarly, assuming StoreDTO has a constructor for all Store fields.
        return new StoreDTO(store.getId(), store.getName(), store.getLocation(), store.getOwnerName());
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        UserDTO userDTO = convertUserToDTO(reservation.getUser());
        StoreDTO storeDTO = convertStoreToDTO(reservation.getStore());
        String statusString = reservation.getStatus().name();


        return new ReservationDTO(
                reservation.getId(),
                userDTO,
                storeDTO,
                reservation.getTime(),
                statusString
        );
    }

    @GetMapping("/{id}")
    public ReservationDTO getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return convertToDTO(reservation);
    }

    @PutMapping("/{id}/confirm")
    public void confirmArrival(@PathVariable Long id) {
        reservationService.confirmArrival(id);
    }


    @GetMapping("/store/{storeId}")
    public List<ReservationDTO> getAllReservationsForStore(@PathVariable Long storeId) {
        return reservationService.getAllReservationsForStore(storeId);
    }

}
