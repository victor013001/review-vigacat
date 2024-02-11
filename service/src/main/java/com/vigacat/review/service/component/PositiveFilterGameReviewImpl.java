package com.vigacat.review.service.component;

import com.vigacat.review.persistence.component.GameReviewPersistence;
import com.vigacat.review.persistence.dto.GameReviewDto;
import com.vigacat.review.service.component.util.GameReviewFilterConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.vigacat.review.service.component.util.GameReviewFilterConstants.MIN_POSITIVE_SCORE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositiveFilterGameReviewImpl implements FilterGameReview {

    private static final String LOG_PREFIX = "Game review positive filter Service >>";

    private final GameReviewPersistence gameReviewPersistence;

    @Override
    public GameReviewFilterConstants.Types getFilterType() {
        return GameReviewFilterConstants.Types.POSITIVE;
    }

    @Override
    public Set<GameReviewDto> getFilteredGameReview(Long gameId) {
        log.info("{} Get game reviews for game id {} with a score higher than {}", LOG_PREFIX, gameId, MIN_POSITIVE_SCORE);
        return gameReviewPersistence.getPositiveGameReviews(gameId, MIN_POSITIVE_SCORE);
    }
}
