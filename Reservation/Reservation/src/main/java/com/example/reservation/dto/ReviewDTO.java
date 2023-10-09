package com.example.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private String content;
    private Double rating;
    private Long userId;
    private Long storeId;

    public ReviewDTO(Long id, String content, Double rating) {
        this.id = id;
        this.content = content;
        this.rating = rating;

    }
}