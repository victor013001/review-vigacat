package com.vigacat.review.web.controller;

import com.vigacat.review.persistence.dto.GameReviewDto;
import com.vigacat.review.service.component.FilterGameReviewService;
import com.vigacat.review.service.component.GameReviewService;
import com.vigacat.review.web.dto.GameReviewRequest;
import com.vigacat.review.web.dto.GameReviewUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameReviewController {

    private final GameReviewService gameReviewService;
    private final FilterGameReviewService filterGameReviewService;

    @GetMapping
    public Set<GameReviewDto> getGameReviews(@RequestParam(name = "game_id") Long gameId) {
        return gameReviewService.getGameReviews(gameId);
    }

    @GetMapping("/{game_id}")
    public Set<GameReviewDto> getGameReviewsFiltered(
            @PathVariable(name = "game_id") Long gameId,
            @NotNull @RequestParam(name = "filter") String type
    ) {
        return filterGameReviewService.getReviews(gameId, type);
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

    @PatchMapping
    @PreAuthorize("hasAnyAuthority('permission::REV_UPDATE_REVIEW')")
    public GameReviewDto updateGameReview(
            @RequestParam(name = "review_id") String reviewId,
            @Valid @RequestBody GameReviewUpdateRequest gameReviewUpdateRequest
    ) {
        return gameReviewService.updateGameReview(
                reviewId,
                gameReviewUpdateRequest.getScore(),
                gameReviewUpdateRequest.getReview()
        );
    }
}
