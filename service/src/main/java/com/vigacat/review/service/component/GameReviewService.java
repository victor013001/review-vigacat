package com.vigacat.review.service.component;

import com.vigacat.review.persistence.dto.GameReviewDto;

import java.util.Set;

public interface GameReviewService {
    Set<GameReviewDto> getGameReviews(Long gameId);
}
