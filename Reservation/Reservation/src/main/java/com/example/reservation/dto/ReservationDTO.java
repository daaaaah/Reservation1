package com.example.reservation.dto;

import com.example.reservation.model.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long id;
    private UserDTO user;
    private StoreDTO store;
    private String time;
    private String date;
    private Long userId;
    private Long storeId;
    private String status;
    // ... 다른 필드들 ...
    public ReservationDTO(Long id, UserDTO user, StoreDTO store, String time, String status) {
        this.id = id;
        this.user = user;
        this.store = store;
        this.time = time;
        this.status = status;
    }
}