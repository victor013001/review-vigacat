package com.vigacat.review.persistence.component;

import com.vigacat.review.persistence.dto.GameReviewDto;

import java.util.Set;

public interface GameReviewPersistence {
    Set<GameReviewDto> getGameReviews(Long gameId);

    GameReviewDto saveGameReview(GameReviewDto gameReviewDto);

    boolean userGameIdReviewExist(String username, Long gameId);
}
