package com.vigacat.review.service.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class GameReviewException extends RuntimeException {



    @Getter
    @RequiredArgsConstructor
    public enum Type {

        TYPE("type", HttpStatus.CONFLICT);

        private final String message;
        private final HttpStatus httpStatus;
    }
}
