package com.vigacat.review.service.component;

import com.vigacat.review.persistence.component.GameReviewPersistence;
import com.vigacat.review.persistence.dto.GameReviewDto;
import com.vigacat.review.service.component.security.util.VigacatSecurityContext;
import com.vigacat.review.service.exceptions.GameReviewException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameReviewServiceImpl implements GameReviewService{

    private static final String LOG_PREFIX = "Game review Service >>";

    private final GameReviewPersistence gameReviewPersistence;
    private final VigacatSecurityContext vigacatSecurityContext;
    private final ModelMapper modelMapper;

    @Override
    public Set<GameReviewDto> getGameReviews(Long gameId) {
        log.info("{} Get game reviews with game id {} ", LOG_PREFIX, gameId);
        return gameReviewPersistence.getGameReviews(gameId);
    }

    @Override
    public GameReviewDto createGameReview(Long gameId, int score, String review) {
        String username = vigacatSecurityContext.getUsernameAuthenticated();
        checkIfReviewExist(username, gameId);
        GameReviewDto gameReviewDto = buildGameReviewDto(gameId, username, score, review);
        log.info("{} Save game review with game id {}, created by {}", LOG_PREFIX, gameId, username);
        return gameReviewPersistence.saveGameReview(gameReviewDto);
    }

    @Override
    public GameReviewDto updateGameReview(String reviewId, int score, String review) {
        GameReviewDto currentGameReviewDto = gameReviewExist(reviewId);
        checkIfIsReviewOwner(currentGameReviewDto.getUsername());
        updateCurrentGameReviewDto(currentGameReviewDto, score, review);
        log.info("{} Updating game review with id {}", LOG_PREFIX, reviewId);
        return gameReviewPersistence.saveGameReview(currentGameReviewDto);
    }

    private void updateCurrentGameReviewDto(GameReviewDto gameReviewDto, int score, String review) {
        modelMapper.map(buildUpdatedGameDto(score,review), gameReviewDto);
    }

    private GameReviewDto buildUpdatedGameDto(int score, String review) {
        return GameReviewDto.builder()
                .score(score)
                .review(review)
                .build();
    }

    private void checkIfIsReviewOwner(String reviewUsername) {
        String currentUser = vigacatSecurityContext.getUsernameAuthenticated();
        if (!reviewUsername.equals(currentUser)) {
            throw new GameReviewException(currentUser, GameReviewException.Type.NOT_REVIEW_OWNER);
        }
    }

    private GameReviewDto gameReviewExist(String reviewId) {
        return gameReviewPersistence.getGameReviewById(reviewId);
    }

    private GameReviewDto buildGameReviewDto(Long gameId, String username, int score, String review) {
        return GameReviewDto.builder()
                .gameId(gameId)
                .username(username)
                .score(score)
                .review(review)
                .date(LocalDate.now())
                .build();
    }

    private void checkIfReviewExist(String username, Long gameId) {
        if (gameReviewPersistence.userGameIdReviewExist(username, gameId)) {
            throw new GameReviewException(username, gameId, GameReviewException.Type.DUPLICATE_GAME_REVIEW);
        }
    }
}
