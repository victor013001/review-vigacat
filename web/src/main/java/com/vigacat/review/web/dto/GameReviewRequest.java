package com.vigacat.review.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameReviewRequest {
    @NotNull(message = "Game id is mandatory")
    private Long gameId;
    @Range(min = 0, max = 100, message = "Game score should be a value between 0 and 100")
    private int score;
    private String review;
}
