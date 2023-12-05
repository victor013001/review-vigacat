package com.vigacat.review.service.component;

import com.vigacat.review.persistence.component.GameReviewPersistence;
import com.vigacat.review.persistence.dto.GameReviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameReviewServiceImpl implements GameReviewService{

    private static final String LOG_PREFIX = "Game review Service >>";

    private final GameReviewPersistence gameReviewPersistence;

    @Override
    public Set<GameReviewDto> getGameReviews(Long gameId) {
        log.info("{} Get game reviews with game id {} ", LOG_PREFIX, gameId);
        return gameReviewPersistence.getGameReviews(gameId);
    }
}
