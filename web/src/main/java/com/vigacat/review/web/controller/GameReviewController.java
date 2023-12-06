package com.vigacat.review.web.controller;

import com.vigacat.review.persistence.dto.GameReviewDto;
import com.vigacat.review.service.component.GameReviewService;
import com.vigacat.review.web.dto.GameReviewRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameReviewController {

    private final GameReviewService gameReviewService;

    @GetMapping
    public Set<GameReviewDto> getGameReviews(@RequestParam(name = "game_id") Long gameId) {
        return gameReviewService.getGameReviews(gameId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('permission::REV_CREATE_REVIEW')")
    public GameReviewDto createGameReview(
            @Valid @RequestBody GameReviewRequest gameReviewRequest
            ) {
        return gameReviewService.createGameReview(
                gameReviewRequest.getGameId(),
                gameReviewRequest.getScore(),
                gameReviewRequest.getReview()
        );
    }
}
