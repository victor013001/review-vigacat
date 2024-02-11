package com.vigacat.review.service.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class GameReviewException extends RuntimeException {

    private final String username;
    private final Long gameId;
    private final Type type;
    private final HttpStatus httpStatus;

    public GameReviewException(String username, Long gameId, Type type) {
        super(type.getMessage());
        this.username = username;
        this.gameId = gameId;
        this.type = type;
        this.httpStatus = type.getHttpStatus();
    }

    public GameReviewException(String username, Type type) {
        super(type.getMessage());
        this.username = username;
        this.gameId = null;
        this.type = type;
        this.httpStatus = type.getHttpStatus();
    }

    @Getter
    @RequiredArgsConstructor
    public enum Type {
        DUPLICATE_GAME_REVIEW("The user already has a review for the game.", HttpStatus.CONFLICT),
        NOT_REVIEW_OWNER("Only the owner of the review can change it.", HttpStatus.FORBIDDEN);

        private final String message;
        private final HttpStatus httpStatus;
    }
}
