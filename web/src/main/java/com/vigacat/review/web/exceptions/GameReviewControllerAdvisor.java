package com.vigacat.review.web.exceptions;

import com.vigacat.review.service.exceptions.GameReviewException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GameReviewControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameReviewException.class)
    protected ResponseEntity<Map<String, Object>> handleGameReviewException(GameReviewException gameReviewException) {
        return ResponseEntity.status(gameReviewException.getHttpStatus())
                .body(buildGameReviewExceptionBody(gameReviewException));
    }

    private Map<String, Object> buildGameReviewExceptionBody(GameReviewException gameReviewException) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("type", gameReviewException.getType());
        body.put("title", gameReviewException.getHttpStatus().name());
        body.put("status", gameReviewException.getHttpStatus().value());
        body.put("game-id", gameReviewException.getGameId());
        body.put("username", gameReviewException.getUsername());
        body.put("detail", gameReviewException.getMessage());
        return body;
    }
}
