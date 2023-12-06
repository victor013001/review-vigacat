package com.vigacat.review.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameReviewDto {
    private Long gameId;
    private String username;
    private LocalDate date;
    private int score;
    private String review;
}
