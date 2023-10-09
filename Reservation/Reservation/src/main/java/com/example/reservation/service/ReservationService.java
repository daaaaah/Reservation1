package com.example.reservation.service;

import com.example.reservation.dto.ReservationDTO;
import com.example.reservation.dto.StoreDTO;
import com.example.reservation.dto.UserDTO;
import com.example.reservation.model.Reservation;
import com.example.reservation.model.ReservationStatus;
import com.example.reservation.model.Store;
import com.example.reservation.model.User;
import com.example.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    //DTO에 제공된 정보를 기반으로 예약 생성. 기존 예약을 확인하고 상충되는 예약이 발견되면 예외를 발생.
    public Reservation makeReservation(ReservationDTO reservationDTO) {

        Reservation reservation = new Reservation();
        reservation.setTime(reservationDTO.getTime());
        reservation.setDate(reservationDTO.getDate());

        List<Reservation> existingReservations = reservationRepository.findAllByStoreId(reservation.getStore().getId());
        for (Reservation existingReservation : existingReservations) {
            if (existingReservation.getDate().equals(reservation.getDate())) {
                throw new RuntimeException("Reservation not available for this date");
            }
        }

        reservation.setStatus(ReservationStatus.PENDING);
        return reservationRepository.save(reservation);
    }

    //ID를 기반으로 예약의 세부 정보를 검색.
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    //매장 도착 시 예약을 "도착" 또는 "승인"으로 표시.
    public void confirmArrival(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null && reservation.getStatus() == ReservationStatus.PENDING) {
            reservation.setStatus(ReservationStatus.APPROVED);
            reservationRepository.save(reservation);
        }
    }


    //각 매장과 연관된 모든 예약을 가져옵니다.
    public List<ReservationDTO> getAllReservationsForStore(Long storeId) {
        List<Reservation> reservations = reservationRepository.findAllByStoreId(storeId);
        return reservations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        User user = reservation.getUser();
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getEmail()
        );

        Store store = reservation.getStore();
        StoreDTO storeDTO = new StoreDTO(
                store.getId(),
                store.getName(),
                store.getOwner().getId()  // assuming Store model has an 'owner' which is of type User
        );

        return new ReservationDTO(
                reservation.getId(),
                userDTO,
                storeDTO,
                reservation.getTime(),
                reservation.getStatus().name()
        );
    }


}