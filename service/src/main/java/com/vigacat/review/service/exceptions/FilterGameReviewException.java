package com.vigacat.review.service.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class FilterGameReviewException extends RuntimeException {

    private final String filterType;
    private final Type type;
    private final HttpStatus httpStatus;

    public FilterGameReviewException(String filterType, Type type) {
        super(type.getMessage());
        this.filterType = filterType;
        this.type = type;
        this.httpStatus = type.getHttpStatus();
    }

    @Getter
    @RequiredArgsConstructor
    public enum Type {

        BAD_REQUEST("The filter type is not valid",HttpStatus.BAD_REQUEST);

        private final String message;
        private final HttpStatus httpStatus;
    }
}
